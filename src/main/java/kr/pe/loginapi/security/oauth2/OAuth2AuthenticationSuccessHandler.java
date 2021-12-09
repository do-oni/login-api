package kr.pe.loginapi.security.oauth2;

import kr.pe.loginapi.config.auth.AppProperties;
import kr.pe.loginapi.exception.BadRequestException;
import kr.pe.loginapi.security.TokenProvider;
import kr.pe.loginapi.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private TokenProvider tokenProvider;
//
//    private AppProperties appProperties;
//
//    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
//
//    @Autowired
//    OAuth2AuthenticationSuccessHandler(TokenProvider tokenProvider, AppProperties appProperties, HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
//        this.tokenProvider = tokenProvider;
//        this.appProperties = appProperties;
//        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String targetUrl = determineTargetUrl(request, response, authentication);
//
//        if(response.isCommitted()) {
//            logger.debug("응답이 이미 커밋 됨" + targetUrl + "로 리다이렉션을 할 수 없음");
//            return;
//        }
//
//        clearAuthenticationAttributes(request, response);
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);
//    }
//
//    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);
//
//        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//            throw new BadRequestException("승인되지 않은 Redirection URI가 있어 인증을 진행할 수 없음");
//        }
//
//        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
//
//        String token = tokenProvider.createToken(authentication);
//
//        return UriComponentsBuilder.fromUriString(targetUrl)
//                .queryParam("token", token)
//                .build().toUriString();
//    }
//
//    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
//        super.clearAuthenticationAttributes(request);
//
//        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookie(request, response);
//    }
//
//    private boolean isAuthorizedRedirectUri(String uri) {
//        URI clientRedirectUri = URI.create(uri);
//
//        return appProperties.getOAuth2().getAuthorizedRedirectUris()
//                .stream()
//                .anyMatch(authorizedRedirectUri -> {
//                    URI authorizedURI = URI.create(authorizedRedirectUri);
//
//                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) && authorizedURI.getPort() == clientRedirectUri.getPort()) {
//                        return true;
//                    }
//                    return false;
//                });
//    }
}
