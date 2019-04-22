package com.datahome.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


/**
 * @Author xl
 * @Description: cache
 * @Date: Create in 2018/10/15 13:56
 */

/*@Component
@Aspect
@Order(2)*/
public class CacheAspect {

    @Resource
    private CacheManager cacheManager;

    private static String[] types = {"java.lang.Integer", "java.lang.Double", "java.lang.Float", "java.lang.Long", "java.lang.Short", "java.lang.Byte", "java.lang.Boolean", "java.lang.Char", "java.lang.String", "int", "double", "long", "short", "byte", "boolean", "char", "float"};

    //使用了缓存的模块
    private static List<String> cacheModel = Arrays.asList("caseCache", "topicCache", "documentCache", "newsCache", "lawCache");

    //缓存 处理
    @Around("execution(* com.datahome.service.*.find*(..))" +
            "||execution(* com.datahome.service.*.update*(..))" +
            "||execution(* com.datahome.service.*.save*(..))" +
            "||execution(* com.datahome.service.*.delete*(..))")
    public Object processCache(ProceedingJoinPoint jp) throws Throwable {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();

        //被访问的类名
        String targetName = toLowerFristChar(targetMethod.getDeclaringClass().getName());

        //方法名
        String methodName = targetMethod.getName();

        //参数列表
        Object[] args = jp.getArgs();

        //缓存中的key值
        String cacheKey = getCacheKey(targetName, methodName, args);

        //获取cache 名称
        String cacheName = getCacheName(targetName);
        Cache cache = null;
        if (cacheModel.contains(cacheName)) {

            //获取对应的cache
            cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                if (isCleanCache(methodName)) {
                    //清除cache
                    cleanCache(cache);
                } else {
                    //从cache中获取缓存数据
                    Cache.ValueWrapper obj = cache.get(cacheKey);
                    if (obj != null) {
                        System.out.println("------------------从  " + cache.getName() + "  中查到了数据！");
                        //若取到数据、直接返回
                        return obj.get();
                    }
                }
            }
        }

        // 执行目标方法
        Object rvt = jp.proceed(args);
        System.out.println("------------------从数据库中查到了数据！");
        if (!isCleanCache(methodName) && cache != null) {
            //将返回值放入缓存
            cache.put(cacheKey, rvt);
        }

        return rvt;
    }

    //根据类名 、方法名、参数 组装缓存中的key值
    public String getCacheKey(String targetName, String methodName, Object[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if ((args != null) && (args.length != 0)) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                // 获取对象类型
                String typeName = arg.getClass().getTypeName();
                for (String t : types) {
                    //1 判断是否是基础类型
                    if (t.equals(typeName)) {
                        sb.append(arg + "; ");
                    } else {
                        //2 通过反射获取实体类属性
                        sb.append(getFieldsValue(arg));
                    }
                    break;
                }
            }
        }
        return sb.toString();
    }

    //解析实体类，获取实体类中的属性
    public static String getFieldsValue(Object obj) {
        //通过反射获取所有的字段，getFileds()获取public的修饰的字段
        // getDeclaredFields获取private protected public修饰的字段
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Field f : fields) {
            //在反射时能访问私有变量
            f.setAccessible(true);
            try {
                for (String str : types) {
                    //暂时未考虑对象实体参数中包含对象实体的情况
                    if (f.getType().getName().equals(str)) {
                        sb.append(f.getName() + ":" + f.get(obj));
                        break;
                    }
                }
            } catch (IllegalArgumentException e) { e.printStackTrace(); } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append(")");
        return sb.toString();
    }


    //获取对应的cache 名称
    public String getCacheName(String targetName) {
        String chcheName = "";
        if (targetName.contains("Mgmt")) {
            chcheName = targetName.substring(0, targetName.lastIndexOf("MgmtService")) + "Cache";
        } else {
            chcheName = targetName.substring(0, targetName.lastIndexOf("Service")) + "Cache";
        }
        return chcheName;
    }

    //清空缓存
    public void cleanCache(Cache cache) {
        String cacheName = cache.getName();
        if ("commonCache".equals(cacheName)) {
            cacheManager.getCache("indexCache").clear();
            cacheManager.getCache("indexDataCache").clear();
        }
        if ("indexCache".equals(cacheName)) {
            cacheManager.getCache("indexDataCache").clear();
        }
        cache.clear();
    }

    //首字母小写
    public String toLowerFristChar(String s) {
        s = s.substring(s.lastIndexOf(".") + 1, s.length());
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    //根据方法名判断是否需要清空缓存
    public boolean isCleanCache(String methodName) {
        if (methodName.startsWith("save") || methodName.startsWith("update") || methodName.startsWith("delete")) {
            return true;
        }
        return false;
    }
}
