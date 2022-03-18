package com.xtl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author 31925
 */
public class CuratorAp {
    
    public static volatile CuratorFramework client = null;
    private static final String ZK_SERVER_IPS = "192.168.188.128:2181";

    public static CuratorFramework getConnection(){
        if(client==null){
            synchronized (CuratorAp.class){
                if(client==null){
                    client= CuratorFrameworkFactory.builder()
                            .connectString(ZK_SERVER_IPS)
                            .connectionTimeoutMs(5000) 
                            .sessionTimeoutMs(5000) 
                            .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                            .build();
                    client.start();
                    System.out.println(client.getState());
                }
            }
        }
        return client;
    }

    public static String create(String path,String value){
        try {
            //若创建节点的父节点不存在会先创建父节点再创建子节点
            return getConnection().create().creatingParentsIfNeeded().forPath("/root"+path,value.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String create(String path,String value,String modeType){
        try {
            if(ObjectUtils.isEmpty(modeType)){
                return null;
            }

            System.out.println(modeType);

            if(String.valueOf(CreateMode.PERSISTENT).equals(modeType)){
                //持久型节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/root"+path,value.getBytes());
            }else if(String.valueOf(CreateMode.EPHEMERAL).equals(modeType)){
                //临时节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/root"+path,value.getBytes());
            }else if(String.valueOf(CreateMode.PERSISTENT_SEQUENTIAL).equals(modeType)){
                //持久类型顺序性节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/root"+path,value.getBytes());
            }else if(String.valueOf(CreateMode.EPHEMERAL_SEQUENTIAL).equals(modeType)){
                //临时类型顺序性节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/root"+path,value.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getData(String path){
        try {
            return new String(getConnection().getData().forPath("/root"+path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getChildren(String path){
        try {
            return getConnection().getChildren().forPath("/root"+path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String setData(String path,String val){
        if(!checkExists(path)){
            create(path,val);
        }
        try {
            getConnection().setData().forPath("/root"+path,val.getBytes());
            return new String(getConnection().getData().forPath("/root"+path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void  delete(String path){
        try {
            getConnection().delete().guaranteed().deletingChildrenIfNeeded().forPath("/root"+path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean  checkExists(String path){
        try {
            Stat s=getConnection().checkExists().forPath("/root"+path);
            return s != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
		if(checkExists("/test")){
			delete("/test");
		}
		System.out.println("创建永久节点："+create("/test/king", "灵主"));
		System.out.println("创建带序号的永久节点："+create("/test/queen", "灵笼", String.valueOf(CreateMode.PERSISTENT_SEQUENTIAL)));
		System.out.println("创建临时节点："+create("/test/joker", "幻境诺德琳", String.valueOf(CreateMode.EPHEMERAL)));
		System.out.println("创建带序号的临时节点："+create("/test/knight", "野良神", String.valueOf(CreateMode.EPHEMERAL_SEQUENTIAL)));

        ExecutorService pool=new ThreadPoolExecutor(2,5,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground((cf, ce) -> {
            System.out.println("code:" + ce.getResultCode());
            System.out.println("type:" + ce.getType());
            System.out.println("线程为:" + Thread.currentThread().getName());
        }, pool)
		.forPath("/root/test/ten","东京食尸鬼".getBytes());

		System.out.println("读取节点： "+getData("/test"));
        String  node = setData("/test/cute", "进击的巨人");
        System.out.println(node);
        System.out.println("读取字节点："+ Objects.requireNonNull(getChildren("/test")));
        System.out.println("删除节点： ");
        System.out.println("测试节点是否存在："+checkExists("/test/joker"));
        delete("/test/joker");
        System.out.println("测试节点是否存在："+checkExists("/test/joker"));
		System.out.println("读取字节点："+ Objects.requireNonNull(getChildren("/test")));
        System.exit(0);
    }
}
