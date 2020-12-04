package com.example.demo.config;

import com.example.demo.security.UserDetailServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.security.web.util.matcher.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        // Các yêu cầu phải login với vai trò user hoặc admin
        // Nếu chưa login, nó sẽ redirect tới trang /login.
        http.authorizeRequests().antMatchers("/my_account","/editInfo","/order_history")//
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        // Các trang chỉ dành cho MANAGER
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access_denied");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and()
                .formLogin()//
                    .loginProcessingUrl("/login") // Submit URL
                    .loginPage("/login")//
                    .defaultSuccessUrl("/login-success")//
                    .failureUrl("/login?error")//
                    .usernameParameter("email")//
                    .passwordParameter("password")

                // Cấu hình cho trang Logout.
                // (Sau khi logout, chuyển tới trang home)
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").deleteCookies("JSESSIONID");
        //Remember me
        http.authorizeRequests().and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60);
        http.exceptionHandling().accessDeniedPage("/access_denied");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}