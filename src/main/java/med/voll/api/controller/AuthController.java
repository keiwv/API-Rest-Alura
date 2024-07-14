package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.users.DataAuthUser;
import med.voll.api.domain.users.User;
import med.voll.api.infra.Security.DataJWTToken;
import med.voll.api.infra.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity AuthUser(@RequestBody @Valid DataAuthUser user)
    {
        Authentication authToken = new UsernamePasswordAuthenticationToken(user.user(), user.password());
        var authUser = authenticationManager.authenticate(authToken);
        var JTWtoken = tokenService.getToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(JTWtoken));
    }
}
