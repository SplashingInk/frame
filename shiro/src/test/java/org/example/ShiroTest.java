package org.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @ClassName ShiroTest
 * @Description TODO
 * @Author xtl
 * @Date 2022/2/11 13:53
 */
public class ShiroTest {

    @Test
    public void test() {
        // 读取 shiro.ini 文件内容
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            System.out.println("someKey 的值：" + value);
        }
        // 登陆
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            System.out.println("用户名不存在:" + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            System.out.println("账户密码 " + token.getPrincipal()  + " 不正确!");
        } catch (LockedAccountException lae) {
            System.out.println("用户名 " + token.getPrincipal() + " 被锁定 !");
        }
        // 认证成功后
        if (currentUser.isAuthenticated()) {
            System.out.println("用户 " + currentUser.getPrincipal() + " 登陆成功！");
            //测试角色
            System.out.println("是否拥有 administrator 角色：" + currentUser.hasRole("administrator"));
            System.out.println("是否拥有 manager 角色：" + currentUser.hasRole("manager"));
            System.out.println("是否拥有 guest 角色：" + currentUser.hasRole("guest"));
            //测试权限
            System.out.println("是否拥有 user:create 权限" + currentUser.isPermitted("user:create"));
            //退出
            currentUser.logout();
        }
    }

    @Test
    public void testFooRealm() {
        // 创建SecurityManager工厂，通过ini配置文件创建 SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        // 创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        // 设置SecurityManager到运行环境中，保持单例模式
        SecurityUtils.setSecurityManager(securityManager);
        // 从SecurityUtils里边创建一个subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备token（令牌）
        // 这里的账号和密码 将来是由用户输入进去
        UsernamePasswordToken token = new UsernamePasswordToken("king", "queen");
        try {
            // 执行认证提交
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        // 是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过：" + isAuthenticated);
    }
}


