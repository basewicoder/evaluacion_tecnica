package com.examen.web.utils;


import com.examen.web.security.AuthUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public final class SecurityUtils {


    public static AuthUser getCurrentUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof AuthUser)) {
            return null;
        }
        return (AuthUser) principal;
    }

    public static String getCurrentUserName() {
        return getCurrentUserDetail().getUsername();
    }

    public static String getCurrentUserNickName() {
        return getCurrentUserDetail().getNickName();
    }

    public static String getCurrentUserId() {
        return getCurrentUserDetail().getUserId();
    }

    public static void refreshUserInfo() {
        String username = getCurrentUserName();
        UserDetailsService userDetailsService = SpringUtils.getBean(UserDetailsService.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails == null ? null : userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
