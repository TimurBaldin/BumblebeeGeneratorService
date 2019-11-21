package com.rufus.bumblebee.configurer.security;

public class SecurityInitializer
        extends AbstractSecurityWebApplicationInitializer {

    public SecurityInitializer() {
        super(WebSecurityConfigurer.class);
    }
}
