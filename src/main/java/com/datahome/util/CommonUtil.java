package com.datahome.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    //timestamp格式化
    public static DateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static SerializerFeature[] feature = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect};

    //fastjson特性
    public static final SerializerFeature[] FEATURE = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect};

    //全局 BeanCopier实例
    static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<String, BeanCopier>();

    //date格式化
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //公开的接口
    public static final List<String> OPEN_PATH = Arrays.asList("/login.do", "/logout.do");

    //姓名特殊字符过滤
    public static final String REGEX_NAME = "^[^`~!@#$%^&*()=+\\[{\\]}\\\\|;:'\"<>/?·！￥…（）—【】、；：‘’“”，《。》？～＠＃％＆×－＝＋｛｝＼｜｀＄＾＊＿［］＇＂＜．＞／]+$";

    //地址特殊字符过滤
    public static final String REGEX_ADDR = "^[^`~!@$%^&*()=+\\[{\\]}\\\\|;:'\"<>/?]+$";

    //证件编号字符过滤
    public static final String REGEX_CARD_NUMBER = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{6,20}$";

    //生日校验 yyyyMMdd
    public static final String DATE = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)$";

    //行政区号
    public static final String DISTRICT_CODE = "^(14|11|13|34|82|63|31|37|64|22|62|15|41|52|46|61|35|54|36|21|51|12|65|42|44|33|50|81|32|45|43|71|23|53|83)\\d{4}$";


    //全局日志
    public static Logger LOGGER(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz.getName());
    }


    //kindeditor专用消息返回器
    public static String editorFormat(Integer error, String url, String message) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("error", error);
        if (error == 0) map.put("url", url);
        else map.put("message", message);
        return JSON.toJSONString(map, FEATURE);
    }


    // status:0代表没有错误；1代表有错误
    public static String format(Integer status, Object message) {
        // message = message == null || "".equals(message) ? "服务器发生异常" : message;
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("status", status);
        map.put("message", message);
        return JSON.toJSONString(map, feature);
    }

    // 参数校验
    public String errorMessage(Errors errors) {
        StringBuffer sb = new StringBuffer();
        for (FieldError error : errors.getFieldErrors()) {
            String s = error.getDefaultMessage();
            //System.out.println(s);
            if (s.contains("override|")) {
                sb.append(s.split("override\\|")[1]).append("|");
            } else {
                sb.append(error.getField() + "的值或格式无效").append("|");
            }
        }
        return sb.toString().replaceAll("\\|$", "");
    }

    //分页
    public static void page(Query query, Integer pageNumber, Integer pageSize) {
        if (pageNumber == null) pageNumber = 1;
        if (pageSize == null) pageSize = 30;
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
    }

    //排序
    //Todo saveTime与createTime 不兼容记得修改!!!!!!!!!!!!
    public static String order(String hql, String order, String sort) {
        if (order == null) order = " DESC ";
        if (sort == null) sort = " saveTime ";
        return hql += " order by " + sort + " " + order;
    }

    //排序
    //Todo saveTime与createTime 不兼容记得修改!!!!!!!!!!!!
    public static String orderCreate(String hql, String order, String sort) {
        if (order == null) order = " DESC ";
        if (sort == null) sort = " createTime ";
        return hql += " order by " + sort + " " + order;
    }

    // 加密
    public static String encodeBase64(String param) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(param.getBytes("utf-8"));
            byte[] raw = md.digest();
            param = new String(new Base64().encode(raw), "ASCII");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }


    //去除首尾空格
    public static String trim(String s) {
        return s == null ? null : s.trim();
    }

    //除\r \n \t \f(分页符) \v(垂直制表符)空格
    public static String cleanAll(String s) {
        return s == null ? null : s.replaceAll("\\s", "");
    }

    //除去全部\r \n \f \v 除去前后\t和空格 将空字符串换成null html转义
    public static String cleanField(String s, Boolean isEscapeHtml) {
        s = clean(s, "\r|\n|\f|\\v|^\\s+|\\s+$", "");
        if (s != null && s.isEmpty()) s = null;
        if (isEscapeHtml) s = StringEscapeUtils.escapeHtml4(s);
        return s;
    }

    //自定义清理 regex = " |\r|\n|\t" 首尾\t regex = "^\t*|\t*$"
    public static String clean(String s, String regex, String replacement) {
        if (s == null) return null;
        if (regex == null) return s;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        return m.replaceAll(replacement == null ? "" : replacement);
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }

    //对象拷贝 性能高于beanutils
    public static void copy(Object srcObj, Object destObj) {
        if (srcObj != null && destObj != null) {
            String key = genKey(srcObj.getClass(), destObj.getClass());
            BeanCopier copier = null;
            if (!BEAN_COPIERS.containsKey(key)) {
                copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
                BEAN_COPIERS.put(key, copier);
            } else {
                copier = BEAN_COPIERS.get(key);
            }
            copier.copy(srcObj, destObj, null);
        }
    }

    //Object属性转为Map ,自动过滤空值 ""、 null、“null”,以及list中指定的值
    public static Map<String, Object> objectToMap(Object obj, String... params) {
        List<String> filterList = null;
        Map<String, Object> map = new LinkedHashMap<>();
        if (obj == null) return map;
        if (null != params && params.length > 0) {
            filterList = Arrays.asList(params);
        }
        try {
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                if (filterList != null && ("saveTime".equals(fieldName) || "updateTime".equals(fieldName) || filterList.contains(fieldName) || "handler".equals(fieldName) || "_methods_".equals(fieldName))) {
                    filterList.remove("fieldName");
                    continue;
                }
                value = StringEscapeUtils.unescapeHtml4(String.valueOf(value));
                map.put(fieldName, value);
            }
        } catch (Exception e) {
        }
        return map;
    }


    //动态字段排序QL语句
    public static String createOrder(String statement, String sort, String order) {
        order = order == null ? " DESC " : order;
        sort = sort == null ? " saveTime " : sort;
        statement += " order by priority ASC , " + sort + " " + order;
        return statement;
    }

    //page分页
    public static void setPage(Query query, Integer pageSize, Integer pageNumber) {
        pageSize = pageSize == null ? 10 : pageSize;
        pageNumber = pageNumber == null ? 1 : pageNumber;
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
    }

    public static void os(HttpServletResponse response, Integer status, String msg) {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter os = null;
        try {
            os = response.getWriter();
            os.println(format(status, msg));
            os.flush();
        } catch (IOException e) {
        } finally {
            if (os != null) os.close();
        }
    }


    public static byte[] desede(byte[] keyBytes, byte[] valueBytes, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(mode, secretKey);
            return cipher.doFinal(valueBytes);
        } catch (Exception e) {
            return null;
        }
    }

    //加密
    public static String encode(String param, String key) {
        if (param == null || param.trim().equals("") || key == null || "".equals(key.trim())) {
            return "";
        }
        byte[] encryptBytes = new byte[0];
        try {
            encryptBytes = desede(key.getBytes("utf-8"), param.getBytes("utf-8"), Cipher.ENCRYPT_MODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return Base64.encodeBase64URLSafeString(encryptBytes);
    }

    //解密
    public static String decode(String token, String key) {
        try {
            byte[] base64TokenBytes = Base64.decodeBase64(token);
            byte[] decryptBytes = desede(key.getBytes("utf-8"), base64TokenBytes, Cipher.DECRYPT_MODE);
            String username = new String(decryptBytes, "utf-8");
            return username;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    //获取客户端ip
    public static String clienIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) ip = request.getRemoteAddr();
        return ip;
    }

    /**
     * 对象1中的字段值转换进入对象2中（只转换相同属性的同一字段）
     *
     * @param obj1
     * @param obje2
     */
    public static void exchangeObj(Object obj1, Object obje2) {
        Class class1 = obj1.getClass();
        Class class2 = obje2.getClass();
        try {
            for (Field field1 : class1.getDeclaredFields()) {
                if ("serialVersionUID".equals(field1.getName())) continue;
                for (Field field2 : class2.getDeclaredFields()) {
                    if ("serialVersionUID".equals(field2.getName())) continue;
                    if (field1.getName().equals(field2.getName()) && field1.getType().equals(field2.getType())) {
                        // 替换属性值
                        Method getMethod = class1.getDeclaredMethod("get" + field1.getName().substring(0, 1).toUpperCase() + field1.getName().substring(1));
                        Method setMethod = class2.getDeclaredMethod("set" + field2.getName().substring(0, 1).toUpperCase() + field2.getName().substring(1), field1.getType());
                        setMethod.invoke(obje2, getMethod.invoke(obj1));
                    }
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static boolean isEmptyString(String str) {
        return str == null || "".equals(str) || str.isEmpty();
    }
    public static boolean objectEmpty(Object obj) {
        return obj == null || "".equals(obj.toString()) || obj.toString().isEmpty();
    }

    public static boolean isEmptyList(List list) {
        return list != null && list.size()>0 ;
    }


    //6位随机验证码
    public static int getRandNum() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

    //校验身份证的最后一位  level 1 最后一位校验 否则一般校验
    public static boolean checkIDCard(String idcord) throws ParseException {
        if (idcord == null || idcord.length() < 18) return false;
        String lastCode = idcord.toLowerCase().trim().substring(17);
        idcord = idcord.substring(0, 17);
        int[] x = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] y = {'1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2'};
        int sum = 0;
        for (int i = 0; i < idcord.length(); i++) {
            int n = idcord.charAt(i) - 48;
            sum = sum + n * x[i];
        }
        sum = sum % 11;
        if (!lastCode.equals(String.valueOf(y[sum]))) {
            return false;
        }
        return true;
    }


    //1居民身份证 2港澳居民居住证 3港澳居民来往内地通行证 4台湾居民居住证 5台湾居民来往大陆通行证 6护照 7军官证 8士官证 9其他 10 其他-无证件

    //校验证件号
    public static boolean checkCardByCardType(Short type, String card, String level) {
        //非法字符校验
        if (type == 7 || type == 8 || type == 9) {
            if (!isValidCharacter(card, true)) return false;
        } else {
            if (!isValidCharacter(card)) return false;
        }

        if (!"2".equals(level)) {
            //居民身份证、港澳居民居住证、台湾居民居住证校验
            if (type == 1 || type == 2 || type == 4) {
                return commonCardValid(type, card);
            }
        }

        //港澳居民来往内地通行证
        if (type == 3) return hmCardValid(card);

        //其他
        return true;
    }

    //非法字符校验
    public static boolean isValidCharacter(String s) {
        return isValidCharacter(s, false);
    }

    //非法字符校验-汉字
    public static boolean isValidCharacter(String s, boolean hanzi) {
        try {
            String regex = "^[a-zA-Z0-9]{6,20}$";
            if (hanzi) regex = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{6,20}$";
            return s.matches(regex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //居民身份证、港澳居民居住证、台湾居民居住证校验
    public static boolean commonCardValid(int type, String s) {
        //总位数校验
        if (s.length() != 18) return false;

        //地址码校验
        if (!isValidCardDistrictCode(type, s.substring(0, 6))) return false;

        //出生日期校验
        if (!isValidCardDate(s.substring(6, 14))) return false;

        //顺序位校验
        if (!isValidCardOrder(s.substring(14, 17))) return false;

        //校验位校验
        return isValidCardCheckbit(s);
    }

    //地址码校验
    public static boolean isValidCardDistrictCode(int type, String s) {
        try {
            String regex;

            //港澳居民居住证
            if (type == 2) regex = "8[1-2]0000";

                //台湾居民居住证
            else if (type == 4) regex = "830000";

                //居民身份证
                //11北京 12天津 13河北 14山西 15内蒙古 21辽宁 22吉林 23黑龙江 31上海 32江苏 33浙江 34安徽 35福建 36江西 37山东
                //41河南 42湖北 43湖南 44广东 45广西 46海南 50重庆 51四川 52贵州 53云南 54西藏 61陕西 62甘肃 63青海 64宁夏 65新疆 71台湾 81香港 82澳门 91国外
            else regex = "(1[1-5]|2[1-3]|3[1-7]|4[1-6]|5[0-4]|6[1-5]|71|8[1-2]|91)\\d{4}";

            return s.matches(regex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //出生日期校验
    public static boolean isValidCardDate(String s) {
        try {
            //校验是否超过当前日期
            Date now = new Date();
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            if (df.parse(s).after(now)) return false;

            //基本校验
            String regex = "(19|20)[0-9]{2}" + //年：1900-2099
                    "(" +
                    "((0[13578]|1[02])(0[1-9]|[1-2][0-9]|3[0-1]))|" + //月日（31天）：1 3 5 7 8 10 12
                    "((0[469]|11)(0[1-9]|[1-2][0-9]|30))|" + //月日（30天）：4 6 9 11
                    "(02(0[1-9]|[1-2][0-9]))" + //月日（29天）：2
                    ")";
            if (!s.matches(regex)) return false;

            //闰年校验
            int year = Integer.parseInt(s.substring(0, 4));
            int month = Integer.parseInt(s.substring(4, 6));
            int date = Integer.parseInt(s.substring(6));
            return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) || !(month == 2 && date == 29);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //顺序位校验
    public static boolean isValidCardOrder(String s) {
        try {
            String regex = "\\d{3}";
            return s.matches(regex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //校验位校验
    public static boolean isValidCardCheckbit(String s) {
        try {
            if (s == null || s.length() < 18) return false;
            String str = s.toLowerCase().trim().substring(17);
            s = s.substring(0, 17);
            int[] x = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
            char[] y = {'1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2'};
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                int n = s.charAt(i) - 48;
                sum = sum + n * x[i];
            }
            sum = sum % 11;
            return str.equals(String.valueOf(y[sum]));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //港澳居民来往内地通行证校验
    public static boolean hmCardValid(String s) {
        try {
            String regex = "[hmHM](\\d{8}|\\d{10})";
            return s.matches(regex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static String getGenderByIdCard(String idCard) {
        String sGender = "";
        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = String.valueOf(1);//男
        } else {
            sGender = String.valueOf(2);//女
        }
        return sGender;
    }



    public static String download(HttpServletResponse response, String srcFilePath, String fileName) throws IOException {
        FileInputStream inStream = new FileInputStream(new File(srcFilePath));
        BufferedInputStream buff = new BufferedInputStream(inStream);
        byte[] b = new byte[1024];
        // 从response对象中得到输出流,准备下载
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/octet-stream; charset=utf-8");
        OutputStream myout = response.getOutputStream();
        // 开始循环下载
        int len;
        while ((len = inStream.read(b)) > 0) {
            myout.write(b, 0, len);
        }
        inStream.close();
        myout.flush();
        buff.close();
        return null;
    }

    //JDBC close
    public static void close(Connection c, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (c != null) c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
