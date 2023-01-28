package bw.org.bocra.portal.config;

import java.util.Arrays;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticatedActionsFilter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//@Configuration
@KeycloakConfiguration
@ComponentScan(basePackageClasses = KeycloakSpringBootConfigResolver.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    private static final String CORS_ALLOWED_HEADERS = "origin,content-type,accept,x-requested-with,Authorization";
    private long corsMaxAge = 60;
    private static final Logger logger = LoggerFactory.getLogger(KeycloakSecurityConfig.class);

    @Override
    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {

        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        grantedAuthorityMapper.setPrefix("ROLE_");

        KeycloakAuthenticationProvider authenticationProvider = keycloakAuthenticationProvider();
        authenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);

        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        super.configure(http);
        http.cors().configurationSource(corsConfigurationSource())
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
            .mvcMatchers(
                "/swagger-ui/*", 
                "/swagger-ui.html", 
                "/webjars/**", 
                "/v3/**", 
                "/swagger-resources/**",
                "/auth/signin",
                "/sector/all",
                "/licensee/search",
                "/complaint",
                "/complaint/reply",
                "/complaint/**",
                "/document/type/all",
                "/document/type/search",
                "/actuator/health"
            ).permitAll()
            .anyRequest().authenticated();
    }

    @Bean
    public FilterRegistrationBean<?> keycloakAuthenticationProcessingFilterRegistrationBean(
            KeycloakAuthenticationProcessingFilter filter) {

        FilterRegistrationBean<?> registrationBean = new FilterRegistrationBean<>(filter);

        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<?> keycloakPreAuthActionsFilterRegistrationBean(KeycloakPreAuthActionsFilter filter) {

        FilterRegistrationBean<?> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<?> keycloakAuthenticatedActionsFilterBean(KeycloakAuthenticatedActionsFilter filter) {

        FilterRegistrationBean<?> registrationBean = new FilterRegistrationBean<>(filter);

        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(
                Arrays.asList("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList(CORS_ALLOWED_HEADERS.split(",")));
        configuration.setMaxAge(corsMaxAge);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // Creating bean keycloakConfigResolver
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
