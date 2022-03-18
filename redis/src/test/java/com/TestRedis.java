package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

public class TestRedis {

    private Jedis jedis;

    @Before
    public void before(){
        String host="192.168.188.130";
        int port= 6379;
        jedis=new Jedis(host,port);
        System.out.println("服务是否在运行："+jedis.ping());
    }

    @Test
    public void testString(){
        jedis.select(0);
        jedis.set("username","Mike");
        String username = jedis.get("username");
        System.out.println(username);
    }

    @Test
    public void testSet(){
        jedis.select(0);
        jedis.sadd("animations","灵笼");
        jedis.sadd("animations","剑风传奇");
        jedis.sadd("animations","一人之下");
        jedis.sadd("animations","斗破苍穹");
        Set<String> animations = jedis.smembers("animations");
        for(String animation:animations){
            System.out.println(animation);
        }
    }


    @Test
    public void testHash(){
        jedis.select(0);
        jedis.hset("boss","username","唐三");
        jedis.hset("boss","age","25");
        jedis.hset("boss","sex","男");
        String username = jedis.hget("boss", "username");
        System.out.println(username);
        Map<String,String> map=new HashMap<>();
        map.put("username","千仞雪");
        map.put("age","27");
        map.put("sex","女");
        jedis.hset("king",map);
        Map<String, String> king = jedis.hgetAll("king");
        for(Map.Entry<String,String> entry:king.entrySet()){
            System.out.println(entry.getKey()+":\t"+entry.getValue());
        }
    }
    
    
    @Test
    public void testList(){
        jedis.select(0);
        jedis.lpush("hobbies","滑雪");
        jedis.lpush("hobbies","溜冰");
        jedis.rpush("hobbies","登山");
        jedis.rpush("hobbies","跳伞");
        jedis.lpush("hobbies","游泳");
        jedis.lpush("hobbies","瑜伽");
        List<String> hobbies = jedis.lrange("hobbies", 0, -1);
        for(String hobby:hobbies){
            System.out.println(hobby);
        }
    }

    @Test
    public void testKeys(){
        Set<String> keys = jedis.keys("*");
        for(String key:keys){
            System.out.println(key);
        }
    }


    @Test
    public void testZSet(){
        jedis.zadd("zset01",70d,"v1");
        jedis.zadd("zset01",50d,"v2");
        jedis.zadd("zset01",90d,"v3");
        jedis.zadd("zset01",80d,"v4");
        System.out.println("----------score递增排列-----------");
        Set<String> s2 = jedis.zrangeByScore("zset01", 50, 100);
        for (String string2 : s2) {
            System.out.println(string2);
        }
    }

    @After
    public void after(){
        jedis.close();
    }
}
