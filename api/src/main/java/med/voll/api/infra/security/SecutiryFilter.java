package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecutiryFilter extends OncePerRequestFilter {

    @Value("${api.security.secret}")
    private String apiSecret;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtniendo token del header
        var token = request.getHeader("Authorization");
        System.out.println(token);
        if(token != null){
            //token obtenido desde request
            token = token.replace("Bearer ","");
            var subject = tokenService.getSubject(token);

            if(subject != null){//validando subject
                //token valido
                var usuario = usuarioRepository.findByLogin(subject);

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);//forzamos el inicio de sesion
            }
        }
        filterChain.doFilter(request, response);//psando al siguiente filtro en la cadena de filtros
    }

}
