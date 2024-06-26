package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import med.voll.api.model.direccion.DireccionDTO;
import med.voll.api.model.medico.*;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping("/registrar")
    @Operation(summary = "Registra un nuevo medico en la base de datos")//documentacion
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedicoDTO medico){
        this.repository.save(new Medico(medico));
    }

    @GetMapping("/listar-medicos")
    @Operation(summary = "Obtiene el listado de medicos")//documentacion
    public ResponseEntity<List<DatosListadoMedicos>> listaMedicos(){
        return ResponseEntity.ok(this.repository.findAll().stream().map(DatosListadoMedicos::new).toList());
    }

    @GetMapping("/listar-medicos-pag")
    @Operation(summary = "Obtiene el listado de medicos con el uso de paginación")//documentacion
    public ResponseEntity<Page<DatosListadoMedicos>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(this.repository.findAll(paginacion).map(DatosListadoMedicos::new));
    }

    //delete logico
    @DeleteMapping("/borrar-medico/{id}")
    @Transactional
    @Operation(summary = "Elimina un medico registrado - inactivo")
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Optional<Medico> medico = this.repository.findById(id);
        if (medico.isPresent()){
            medico.get().desarctivarMedico();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar-datos")
    @Transactional
    @Operation(summary = "Actualiza los datos de un medico existente")
    public ResponseEntity actualizarDatos(@RequestBody @Valid DatosActualizarDTO datos){
        Medico medico = this.repository.getReferenceById(datos.id());
        medico.actualizarDatosMedico(datos);

        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DireccionDTO(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())));
    }
}
