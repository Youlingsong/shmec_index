package com.datahome.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @Author xl
 * @Description: redis 配置类
 * @Date: Create in 2019/1/25 17:41
 */

//@Configuration
@PropertySource(value = {"classpath:redis.properties"}, encoding = "utf-8")
public class RedisConfig {

    @Value("${redis.password}")
    private String password;

    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.minIdle}")
    private Integer minIdle;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWait}")
    private Long maxWait;

    @Value("${redis.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${redis.cluster.nodes}")
    private String nodes;

    @Value("${redis.cluster.maxRedirects}")
    private Integer maxRedirects;

    @Value("${redis.cluster.timeout}")
    private Long timeOut;

    @Value("${redis.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;

    @Value("${redis.session.maxInactiveIntervalInSeconds}")
    private Integer maxInactiveIntervalInSeconds;

    /**
     * redis 连接池
     */
    @Bean
    public GenericObjectPoolConfig jedisPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        // 最大建立连接等待时间
        config.setMaxWaitMillis(maxWait);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        config.setNumTestsPerEvictionRun(3);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        config.setTimeBetweenEvictionRunsMillis(-1);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        config.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        config.setTestWhileIdle(testWhileIdle);
        // 最大空闲数，数据库连接的最大空闲时间, 默认8个
        config.setMaxIdle(maxIdle);
        //最小空闲连接数, 默认0
        config.setMinIdle(minIdle);
        //最大连接数, 默认8个
        config.setMaxTotal(maxTotal);
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        config.setBlockWhenExhausted(true);
        return config;
    }

    @Bean(name = "jedisCluster")
    public JedisCluster getJedisCluster(GenericObjectPoolConfig config) {
        JedisCluster jedisCluster=null;
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        String[] hostAndPort = nodes.split(",");
        for (String hp : hostAndPort) {
            String[] str = hp.split(":");
            String host = str[0];
            Integer port = Integer.parseInt(str[1]);
            hostAndPorts.add(new HostAndPort(host, port));
        }
        if (password == null || "".equals(password)) {
             jedisCluster = new JedisCluster(hostAndPorts, 50000, 50000, 5, config);
        }
        //JedisCluster jedisCluster = new JedisCluster(hostAndPorts, 50000, 50000, 5, password, config);

        //测试是否连接正常
        if (jedisCluster!=null&&"OK".equals(jedisCluster.set("feifei", "feifei"))) {
            System.out.println("redis连接成功");
        } else {
            System.out.println("redis连接失败");
            throw new RuntimeException();
        }
        Integer currentUserCount = getCurrentUserCount(jedisCluster);
        System.out.println(currentUserCount);
        List<String> feifei = getKeys(jedisCluster,1000, "feifei");
        return jedisCluster;
    }

    //获取当前在线人数
    public Integer getCurrentUserCount(JedisCluster jedisCluster) {
        int now = 0;
        if (jedisCluster == null) return now;

        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for (String k : clusterNodes.keySet()) {
            Jedis jedis = null;
            try {
                JedisPool jp = clusterNodes.get(k);
                jedis = jp.getResource();
                String info = jedis.info("replication");
                if (!info.contains("role:master")) continue;

                //scan
                ScanParams scanParams = new ScanParams();
                scanParams.match("fei*").count(1);
                String cursor = "0";
                boolean init = true;

                while (!"0".equals(cursor) || init) {
                    init = false;
                    ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
                    cursor = scanResult.getStringCursor();
                    List<String> list = scanResult.getResult();
                    int size = list.size();
                    if (size > 0) now += list.size();
                }
            } catch (Exception e) {
            } finally {
                if (jedis != null) jedis.close();
            }
        }
        return now;
    }

    //根据规则查询所有的key
    public List<String> getKeys(JedisCluster jedisCluster,Integer count, String... pattern) {
        List<String> keys = new ArrayList<>();
        if (jedisCluster == null) return keys;

        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for (String k : clusterNodes.keySet()) {
            Jedis jedis = null;
            try {
                JedisPool jp = clusterNodes.get(k);
                jedis = jp.getResource();
                String info = jedis.info("replication");
                if (!info.contains("role:master")) continue;

                //scan
                ScanParams scanParams = new ScanParams();
                for (String p : pattern) {
                    scanParams.match(p);
                    scanParams.count(count);

                    String cursor = "0";
                    boolean init = true;

                    while (!"0".equals(cursor) || init) {
                        init = false;
                        ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
                        cursor = scanResult.getStringCursor();
                        keys.addAll(scanResult.getResult());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (jedis != null) jedis.close();
            }
        }
        return keys;
    }
}
