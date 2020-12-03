package com.example.demo.config;

import javax.sql.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.security.web.util.matcher.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    private final String USERS_QUERY = "select email, password, active from user where email=?";
    private final String ROLES_QUERY = "select email, role.role_name from user inner join user_role on (user.id = user_role.user_id) inner join role on (user_role.role_id=role.id) where user.email=?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();

        // Các yêu cầu phải login với vai trò user hoặc admin
        // Nếu chưa login, nó sẽ redirect tới trang /login.
//        http.authorizeRequests().antMatchers("/category").permitAll();
        http.authorizeRequests().antMatchers("/my_account","/editInfo")//
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        // Các trang chỉ dành cho MANAGER
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access_denied");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//

                //
                .loginProcessingUrl("/login") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/home")//
                .failureUrl("/login?error=true")//
                .usernameParameter("email")//
                .passwordParameter("password")

                // Cấu hình cho trang Logout.
                // (Sau khi logout, chuyển tới trang home)
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home").deleteCookies("JSESSIONID");
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