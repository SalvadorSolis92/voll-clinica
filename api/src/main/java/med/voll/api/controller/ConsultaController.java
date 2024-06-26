package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import med.voll.api.model.consulta.DatosDetalleConsulta;
import med.voll.api.service.AgendaDeConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agenda")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService agendaService;

    @PostMapping("/consultas")
    @Operation(summary = "registra una consulta en la base de datos")//para documentacion
    public ResponseEntity agendarCita(@RequestBody @Valid DatosAgendaConsulta datos){

        var response = agendaService.agendar(datos);

        return ResponseEntity.ok(response);
    }
}
