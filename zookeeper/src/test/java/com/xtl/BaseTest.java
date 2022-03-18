package com.xtl;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BaseTest {

    ZooKeeper zk=null;

    @Before
    public void init() throws Exception {
        final CountDownLatch cdl = new CountDownLatch(1);

        //ip端口，连接超时时间，监听者
        zk = new ZooKeeper("192.168.188.128:2181", 3000, event -> {
            if(event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                System.out.println("连接成功！");
                cdl.countDown();
            }
        });
        cdl.await();
    }

    /*
     * 创建新节点
     */
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        /*
        create(final String path, byte data[], List<ACL> acl,
                CreateMode createMode)
         第一个参数：节点的路径
         第二个参数：节点存储的路径
         第三个参数：访问控制权限
         第四个参数：节点的类型
         */
        zk.create("/joker","假面骑士剑".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void getNodeData() throws InterruptedException, KeeperException {
        //获取数据
        //参数：节点，监听者，节点的状态信息如时间戳等
        byte[] byteDate = zk.getData("/father/doctor", null, null);
        System.out.println(new String(byteDate));
    }

    /**
     * 获取子节点并监听
     */
    @Test
    public void watchChildren() throws KeeperException, InterruptedException {
        /*
         getChildren(final String path, Watcher watcher)
         第一个参数：节点路径
         第二个参数：监听器对象
         */
        //当Server端口的Node发生改变那么Server就会将该事件通知Client那么Client就会调用此方法
        List<String> children = zk.getChildren("/father", event -> {
            //在此方法中写收到监听事件后所需要做的操作。
            System.out.println(Thread.currentThread().getName());
            //打印信息
            System.out.println("节点信息发生了变化");
        });

        for (String child : children) {
            System.out.println(child);
        }

        //给一定时间监控变化
        Thread.sleep(30000);
    }

    /**
     * 查看节点状态 - 判断节点是否存在
     */
    @Test
    public void nodeExists() throws KeeperException, InterruptedException {
        /*
            exists(String path, boolean watch)
            第一个参数 ：节点的路径
            第二个参数 ： 是否使用总监听对象（new Zookeeper时创建的监听对象）
                不用监听。
            注意：如果返回值为null则表示节点不存在
         */
        Stat exists = zk.exists("/father/doctor", false);

        if (exists == null){
            System.out.println("节点不存在");
        }else {
            System.out.println("节点存在");
        }

        Stat stat = zk.exists("/father/boy", false);

        if (stat == null){
            System.out.println("节点不存在");
        }else {
            System.out.println("节点存在");
        }
    }

    @After
    public void close() throws InterruptedException {
        zk.close();
    }
}
