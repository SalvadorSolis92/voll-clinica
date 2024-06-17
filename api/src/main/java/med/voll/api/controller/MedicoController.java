package med.voll.api.controller;

import med.voll.api.model.medico.DatosRegistroMedico;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @GetMapping("/registrar")
    public void registrarMedico(@RequestBody DatosRegistroMedico medico){
        System.out.println("registrando medico"+medico);
    }
}
