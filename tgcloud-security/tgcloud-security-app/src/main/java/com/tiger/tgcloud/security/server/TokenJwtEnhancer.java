
package com.tiger.tgcloud.security.server;

import com.tiger.tgcloud.security.core.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;


/**
 * The class Token jwt enhancer.
 *
 * @author
 */
public class TokenJwtEnhancer implements TokenEnhancer {

    /**
     * Enhance o auth 2 access token.
     *
     * @param accessToken          the access token
     * @param oAuth2Authentication the o auth 2 authentication
     * @return the o auth 2 access token
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> info = new HashMap<>(8);
        info.put("timestamp", System.currentTimeMillis());
        Authentication authentication = oAuth2Authentication.getUserAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Object principal = authentication.getPrincipal();
            SecurityUser securityUser = (SecurityUser) principal;
            info.put("uid", securityUser.getUserId());
            info.put("uname", securityUser.getUsername());
            info.put("gid", securityUser.getGroupId());
            info.put("gname", securityUser.getGroupName());
            info.put("nick", securityUser.getNickName());
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }

}
