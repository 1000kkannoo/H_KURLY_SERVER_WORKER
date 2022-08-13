package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Config;


import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Jwt.JwtAccessDeniedHandler;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Jwt.JwtAuthenticationEntryPoint;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Jwt.JwtSecurityConfig;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity // 기본적인 웹 보안을 활성화 시킬거라는 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true) //PreAuthorize 어노테이션을 메소드 단위로 사용하기 위해 선언
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 생성한 jwt 설정들 의존성 주입
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 패스워드는 Bcrypt 사용
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler)


                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/register").permitAll()
                .anyRequest().authenticated()

                .and().apply(new JwtSecurityConfig(tokenProvider));
    }
}