package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class RedisClusterTest {

    JedisCluster jedisCluster=null;

    @Before
    public void init(){
        Set<HostAndPort> set=new HashSet<>();
        set.add(new HostAndPort("192.168.188.130", 6382));
        set.add(new HostAndPort("192.168.188.130", 6383));
        set.add(new HostAndPort("192.168.188.130", 6384));
        set.add(new HostAndPort("192.168.188.130", 6392));
        set.add(new HostAndPort("192.168.188.130", 6393));
        set.add(new HostAndPort("192.168.188.130", 6394));
        jedisCluster=new JedisCluster(set);
    }

    @Test
    public void testCluster(){
        jedisCluster.set("animations","灵主，不良人，灵笼");
        System.out.println(jedisCluster.get("my_hobby"));
    }

    @After
    public void close(){
        jedisCluster.close();
    }
}
