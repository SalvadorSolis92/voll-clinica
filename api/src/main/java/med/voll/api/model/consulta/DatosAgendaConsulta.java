package med.voll.api.model.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendaConsulta(
        Long id,
        @NotNull(message = "id del paciente no puede ser nulo")
        Long idPaciente,
        Long idMedico,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime fecha,
        Especialidad especialidad
) {
}
