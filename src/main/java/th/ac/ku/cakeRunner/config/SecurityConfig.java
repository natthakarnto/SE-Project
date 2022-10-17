package th.ac.ku.cakeRunner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import th.ac.ku.cakeRunner.service.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService service;

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*","/", "/home","/cakes","/css/**","/js/**","/images/**","/order","/register","/contact").permitAll()
                .antMatchers("/cakes/**","order/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT username, password, 'true' FROM users WHERE username=?")
                .authoritiesByUsernameQuery(
                        "SELECT username, 'ROLE_USER' FROM users WHERE username=?");

        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("user1").password(passwordEncoder().encode("user1")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
