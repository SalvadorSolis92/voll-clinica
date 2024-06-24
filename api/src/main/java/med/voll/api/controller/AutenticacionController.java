package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.infra.security.DatosJWToken;
import med.voll.api.infra.security.TokenService;
import med.voll.api.model.usuarios.DatosAutenticacionUsuario;
import med.voll.api.model.usuarios.Usuario;
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
@RequestMapping("/autenticar")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager autenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity autenticacion(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacion){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.login(), datosAutenticacion.clave());
        var usuarioAutenticado = autenticationManager.authenticate(authenticationToken);
        var token = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWToken(token));
    }
}
