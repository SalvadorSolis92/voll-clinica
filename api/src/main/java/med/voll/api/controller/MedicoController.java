package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.medico.DatosRegistroMedicoDTO;
import med.voll.api.model.medico.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @GetMapping("/registrar")
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedicoDTO medico){
        this.repository.save(new Medico(medico));
    }
}
