package com.jiyun.springshiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;

/**
 * Created by lishan on 2018/6/6.
 */
public class CustomRealm extends AuthorizingRealm {

    HashMap<String,String> map = new HashMap<>();
    {
        map.put("lishan","1234");
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        //获取到密码
        String password = getByUsernamePassword(username);
        if(password==null){
            return null;

        }

        //创建返回的实力
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("lishan",password,"admin");

        //设置md5
        info.setCredentialsSalt(ByteSource.Util.bytes("password"));

        return info;
    }

    //获取用户的密码
    private String getByUsernamePassword(String username) {

        return map.get(username);
    }


    //获取md5
    public static void main(String[] args) {
        //cb28e00ef51374b841fb5c189b2b91c9
        Md5Hash md = new Md5Hash("123456","password");
        System.out.println(md);
    }
}
