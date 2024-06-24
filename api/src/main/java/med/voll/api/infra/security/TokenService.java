package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String generarToken(){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secreto-medico");
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject("salvador.solis")
                    .sign(algorithm);

        }catch (JWTCreationException e){
            throw new RuntimeException();
        }
    }
}
