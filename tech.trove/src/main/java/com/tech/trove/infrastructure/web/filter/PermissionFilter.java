package com.tech.trove.infrastructure.web.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tech.trove.infrastructure.common.constant.Role;
import com.tech.trove.infrastructure.common.constant.TechTroveCoreConstant;
import com.tech.trove.infrastructure.web.exception.BadRequestException;
import com.tech.trove.usermanagement.application.dto.RoleDto;
import com.tech.trove.usermanagement.application.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * The type Permission filter.
 */
@Component
@Order
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PermissionFilter extends OncePerRequestFilter {
    private final UserServiceImpl userServiceImpl;

    @Value("${securityApp.enabledByOperationActionsCurrentUser}")
    private boolean enabledByOperationActionsCurrentUser;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws
            ServletException, IOException {
        if(!enabledByOperationActionsCurrentUser || StringUtils.isBlank(request.getHeader(TechTroveCoreConstant.AUTHORIZATION))) {

            filterChain.doFilter(request, response);
            return;
        }

        /* enabled filter:*/
        String token = request.getHeader(TechTroveCoreConstant.AUTHORIZATION).replace(TechTroveCoreConstant.BEARER,"");
        DecodedJWT jwt = this.decodeToken(token);
        String userId = jwt.getClaim("username").asString();
        String scope = jwt.getClaim("scope").asString();

        if(!scope.equals("aws.cognito.signin.user.admin") || StringUtils.isBlank(userId)){
           filterChain.doFilter(request, response);
           return;
        }

        log.info("permission filter user of token {} getting operationActions ...",userId);
        try{
            Set<GrantedAuthority> setAuthorities = getSetAuthorities(userId);

            Collection<? extends GrantedAuthority> authorities =  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            setAuthorities.addAll(authorities);
            final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(String.format("ROLE_%s", Role.USER.name()));
            setAuthorities.add(simpleGrantedAuthority);
            PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                    SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                    setAuthorities);


            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(BadRequestException e){
            log.error(e.getMessage(),e);
        }
        filterChain.doFilter(request, response);
    }

    private Set<GrantedAuthority> getSetAuthorities(String userId) {
        Set<GrantedAuthority> setGrantedAuthority = new HashSet<>();
        RoleDto role = userServiceImpl.retrieveUserAuthoritiesByUserId(userId);
        Set<String> setDomainOperations = new HashSet<>(role.getDomainOperations());
        setDomainOperations.add(role.getName());
        setDomainOperations.forEach((String domainOperation) -> setGrantedAuthority.add(new SimpleGrantedAuthority(domainOperation)));
        return setGrantedAuthority;
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch(){
        return true;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch(){
        return true;
    }

    private DecodedJWT decodeToken(String token) throws BadRequestException {
        JWT jwt = new JWT();
        DecodedJWT decodedJWT = jwt.decodeJwt(token);
        try {
            return decodedJWT;
        } catch(JWTDecodeException e){
            throw new BadRequestException("Error to validate request while decoding");
        }
    }

}