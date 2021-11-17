package ru.mattgroy.conspectusshare.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import ru.mattgroy.conspectusshare.models.GoogleUser;
import ru.mattgroy.conspectusshare.services.GoogleUserService;
import ru.mattgroy.conspectusshare.services.UserServiceImpl;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {

        AuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/");

        http.antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/", "/error", "/webjars/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .csrf().disable()
//                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
                //.and()
                .logout()
                    .logoutSuccessUrl("/").permitAll()
                .and()
                .oauth2Login()
                    .userInfoEndpoint()
                    .userService(googleUserService)
                .and()
                .successHandler((request, response, authentication) -> {
                    GoogleUser oauthUser = (GoogleUser) authentication.getPrincipal();
                    userService.processOAuthPostLogin(oauthUser);
                    response.sendRedirect("/conspectus/register");
                })
                .failureHandler((request, response, exception) -> {
                    request.getSession().setAttribute("error.message", exception.getMessage());
                    handler.onAuthenticationFailure(request, response, exception);
                });
    }

    @Autowired
    private GoogleUserService googleUserService;

    @Autowired
    private UserServiceImpl userService;
}
