package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.medico.DatosListadoMedicos;
import med.voll.api.model.medico.DatosRegistroMedicoDTO;
import med.voll.api.model.medico.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping("/registrar")
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedicoDTO medico){
        this.repository.save(new Medico(medico));
    }

    @GetMapping("/listar-medicos")
    public List<DatosListadoMedicos> listaMedicos(){
        return this.repository.findAll().stream().map(DatosListadoMedicos::new).toList() ;
    }

    @GetMapping("/listar-medicos-pag")
    public Page<DatosListadoMedicos> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion){
        return this.repository.findAll(paginacion).map(DatosListadoMedicos::new);
    }










    //delete logico
    @DeleteMapping("borrar-medico/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = this.repository.findById(id);
        medico.desarctivarMedico();
    }

}
