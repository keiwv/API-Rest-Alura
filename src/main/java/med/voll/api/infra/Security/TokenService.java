package med.voll.api.infra.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService
{

    @Value("${api.security.secret}")
    private String apiSecret;

    public String getToken(User user)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(user.getUser())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getDateExpired())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getSubject(String token) {
        if (token == null)
        {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);

        } catch (JWTVerificationException exception) {
            System.err.println("JWT verification failed: " + exception.getMessage());
            throw new RuntimeException("Token verification failed", exception);
        }

        String subject = verifier != null ? verifier.getSubject() : null;
        if (subject == null) {
            throw new RuntimeException("Verifier invalid or subject is null");
        }

        return subject;
    }
    private Instant getDateExpired() {
        return LocalDateTime.now(ZoneOffset.UTC).plusHours(2).toInstant(ZoneOffset.UTC);
    }

}
