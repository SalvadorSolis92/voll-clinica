package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.usuarios.DatosAutenticacionUsuario;
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

    @PostMapping("/login")
    public ResponseEntity autenticacion(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacion){
        Authentication token = new UsernamePasswordAuthenticationToken(datosAutenticacion.login(), datosAutenticacion.clave());
        autenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
