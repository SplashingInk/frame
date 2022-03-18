package org.example;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @author 31925
 */
public class CodeTest {
    public static void main(String[] args) {
        String phone="15751218750";
        sendCode(phone);
        boolean flag = checkCode(phone, "861217");
        if(flag){
            System.out.println("验证码验证通过");
        }else{
            System.out.println("验证码验证失败");
        }
    }

    private static String createCode(){
        Random random=new Random();
        StringBuilder code=new StringBuilder();
        for (int i = 0; i <6 ; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    private static void sendCode(String phone){
        Jedis jedis=new Jedis("192.168.188.130",6379);
        String countKey=phone+":countKey";
        String count=jedis.get(countKey);
        if(count==null){
            jedis.setex(countKey,24*60*60,"1");
        }else if(Integer.parseInt(count)<=2){
            jedis.incr(countKey);
        }else{
            System.out.println("该手机验证码次数已达上限，清明日再尝试！");
            jedis.close();
            return;
        }
        String redisCode = createCode();
        String codeKey=phone+":codeKey";
        jedis.setex(codeKey,120,redisCode);
        jedis.close();
    }

    private static  boolean checkCode(String phone,String code){
        Jedis jedis=new Jedis("192.168.188.130",6379);
        String codeKey=phone+":codeKey";
        String redisCode = jedis.get(codeKey);
        return redisCode.equals(code);
    }
}
