package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import med.voll.api.model.consulta.DatosDetalleConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class ConsultaController {


    public ResponseEntity agendarCita(@RequestBody @Valid DatosAgendaConsulta datos){
        return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));
    }
}
