package pl.mateusz.testblog.configurate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.mateusz.testblog.models.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/posts").hasAnyRole("ADMIN")  // jakie url chcemy zabespieczyć dla roli ADMIN
                .anyRequest().permitAll()           //pozostae bez zabespieczeń
            .and()
                .csrf().disable()       // poczytać
                .formLogin()            //opis procesu logowania
                    .loginPage("/login")        //strona logowania na naszej stronie
                    .failureUrl("/login?error=true")   //logika po nieudanym logowaniu
                    .defaultSuccessUrl("/")                //po udanym logowaniu
                    .loginProcessingUrl("/login-process")       //post na dany URL
                    .usernameParameter("name")              //podajemy z kąd ma brać ligin
                    .passwordParameter("password")             //skąd ma braćhasło
            .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                ;
    }



    //klasa odpowiedzalna za wyciąganie danych urzytkowników do logowania
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("test").password("$2a$10$UfYwahfc9Z9T9q2xnlgzZe6V/u3lhzHpjfsLCsVSbj17kvueozCsS").roles("USER")
//                .and()
//                .withUser("admin").password("$2a$10$kAbvunL94yR1L2Ppqd637uVmySR.x33PD7DJ1WANQYvega.pj1KaC").roles("USER","ADMIN");

        auth.userDetailsService(customUserDetailsService);
    }
}
