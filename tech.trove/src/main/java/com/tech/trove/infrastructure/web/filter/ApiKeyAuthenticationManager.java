package com.tech.trove.infrastructure.web.filter;

import com.tech.trove.infrastructure.common.constant.Role;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Api key authentication manager.
 */
@NoArgsConstructor
public class ApiKeyAuthenticationManager implements AuthenticationManager {

    private final Map<String, Role> apikeyAuthentications = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String apiKey = String.valueOf(authentication.getPrincipal());
        if (apikeyAuthentications.containsKey(apiKey)) {
            return createPreAuthenticatedAuthenticationToken(authentication, apikeyAuthentications.get(apiKey));
        }
        authentication.setAuthenticated(false);
        return authentication;
    }

    /**
     * Add apikey auth api key authentication manager.
     *
     * @param apikey   the apikey
     * @param authRole the auth role
     * @return the api key authentication manager
     */
    public ApiKeyAuthenticationManager addApikeyAuth(String apikey, Role authRole) {
        if (apikey == null) {
            return this;
        }

        Assert.notNull(authRole, () -> "Authority role is needed for the Api-key/token");

        if (StringUtils.hasText(apikey)) {
            if (authRole == Role.CLIENT || authRole == Role.ADMIN) {
                String SERVICES_API_KEY_SEPARATOR = "_@_";
                final String[] apiKeys = apikey.split(SERVICES_API_KEY_SEPARATOR);
                apikeyAuthentications.put(apiKeys[0], authRole);
                if (apiKeys.length > 1) {
                    apikeyAuthentications.put(apiKeys[1], authRole);
                }
            } else {
                apikeyAuthentications.put(apikey, authRole);
            }
        }
        return this;
    }

    private PreAuthenticatedAuthenticationToken createPreAuthenticatedAuthenticationToken(Authentication authentication, Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.name())));
        return new PreAuthenticatedAuthenticationToken(authentication.getPrincipal(), null, authorities);
    }

}
