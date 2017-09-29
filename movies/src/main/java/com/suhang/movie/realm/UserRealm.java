package com.suhang.movie.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

import com.suhang.movie.model.User;
import com.suhang.movie.service.UserService;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:21
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user;
        if (token.getPrincipal() instanceof Long) {
            user = userService.findById((Long) token.getPrincipal());
        } else {
            user = userService.findByUsername((String) token.getPrincipal());
        }
        if (user == null) {
            throw new UnknownAccountException();
        }
        // AuthenticatingRealm -> CredentialsMatcher -> do match
        return new SimpleAuthenticationInfo(
            user,
            user.getPassword(),
            new SimpleByteSource(user.getCredentialsSalt()),
            getName()  // realm name
        );
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
        User user = (User) super.getAvailablePrincipal(principals);
        return user.getUsername();
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        User user = (User) super.getAvailablePrincipal(principals);
        return user.getUsername();
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
