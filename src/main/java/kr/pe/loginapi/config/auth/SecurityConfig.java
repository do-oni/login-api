package kr.pe.loginapi.config.auth;

import kr.pe.loginapi.domain.Role;
import kr.pe.loginapi.security.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
//    private final CustomUserDetailsService customUserDetailsService;
//    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
//    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
//    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

//    @Bean
//    public TokenAuthenticationFilter tokenAuthenticationFilter() {
//        return new TokenAuthenticationFilter();
//    }

//    @Bean
//    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
//        return new HttpCookieOAuth2AuthorizationRepository();
//    }

    // Authorization에 사용할 userDetailService와 passord Encoder를 정의
//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                .userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

    // Custom Security Config에서 사용할 password encoder를 BCryptPasswordEncoder로 정의
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 외부에서 사용하기 위해서 AuthenticationManagerBean을 이용하여
    // SpringSecurity 밖으로 Authentication을 빼내야 함(@Bean 설정 필요)
    // @Autowired 사용시 에러
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // cors 허용
                    .and()
                .sessionManagement()  // sessionCreationPolicy를 stateless 정의하여 session 사용 안함 설정
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 토큰 사용
                    .and()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
                .csrf()
                    .disable()
//                .formLogin()
//                    .disable()
                .httpBasic()
                    .disable()
//                .headers().frameOptions().disable()
//                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasAnyRole(Role.GUEST.name(), Role.USER.name(), Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                    .authorizationEndpoint()
                    // client에서 처음 로그인 시도 URI
                    .baseUri("/oauth2/authorization")
//                    .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
                    .and()
                .userInfoEndpoint()
                    .userService(customOAuth2UserService);
//                    .and()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .failureHandler(oAuth2AuthenticationFailureHandler);
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

//                .logout()
//                .logoutSuccessUrl("/index.html")
//                .and()
//                .oauth2Login()
//                .defaultSuccessUrl("/index.html")
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);

//                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                // authorizeRequests
//                // URL별 권한 관리를 설정하는 옵션의 시작점이다. authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있다.
//                .authorizeRequests()
//                // antMatchers
//                // 권한 관리 대상을 지정하는 옵션이다.
//                // URL, HTTP 메소드별로 관리가 가능하다. 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 관한을 준다.
//                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console?**").permitAll()
//                // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 한다.
//                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                // anyRequest
//                // 설정된 값 이외의 나머지 URL들을 나타낸다. authenticated()를 추가하여 나머지 URL들은 모두 인증된 사용자들(로그인한 사용자들)에게만 허용한다.
//                .anyRequest().authenticated()
//                .and()
//                // .logout().logoutSuccessUrl("/")
//                // 로그아웃 기능에 대한 여러 설정의 진입점이다. 로그아웃 성공시 "/" 주소로 이동한다.
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                // oauth2Login
//                // OAuth 2 로그인 기능에 대한 여러 설정의 진입점이다.
//                .oauth2Login()
//                // userInfoEndpoint
//                // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 떄의 설정들을 담당한다.
//                .userInfoEndpoint()
//                // userService
//                // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
//                // 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능을 명시할 수 있다.
//                .userService(customOAuth2UserService);
    }
}
