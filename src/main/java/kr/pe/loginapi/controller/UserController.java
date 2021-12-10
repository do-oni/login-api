package kr.pe.loginapi.controller;

import kr.pe.loginapi.domain.OAuthUser;
import kr.pe.loginapi.repository.OAuthUserRepository;
import kr.pe.loginapi.security.CurrentUser;
import kr.pe.loginapi.security.UserPrincipal;
import kr.pe.loginapi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final OAuthUserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public OAuthUser getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
