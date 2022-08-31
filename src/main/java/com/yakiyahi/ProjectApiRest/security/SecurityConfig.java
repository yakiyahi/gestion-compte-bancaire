package com.yakiyahi.ProjectApiRest.security;
import com.yakiyahi.ProjectApiRest.security.filters.CustomerAuthorizationFilter;
import com.yakiyahi.ProjectApiRest.security.filters.JwtAuthentificationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/*").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/*/*").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/allusers/save").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");

        http.authorizeHttpRequests().antMatchers(HttpMethod.DELETE,"/*").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");
        http.authorizeHttpRequests().antMatchers(HttpMethod.DELETE,"/all*/*").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");

        //configuration du droit pour supprimer et modifier un utilisateur
        http.authorizeHttpRequests().antMatchers(HttpMethod.DELETE,"/*/*/*").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");
        http.authorizeHttpRequests().antMatchers(HttpMethod.PUT,"/*").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");
        http.authorizeHttpRequests().antMatchers(HttpMethod.PUT,"/*/*").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");

        http.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/*").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN","ROLE_USER","ROLE_CUSTOMER");

        http.authorizeHttpRequests().anyRequest().authenticated();

        http.addFilter(new JwtAuthentificationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomerAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
