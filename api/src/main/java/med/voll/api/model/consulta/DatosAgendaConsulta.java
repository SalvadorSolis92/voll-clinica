package med.voll.api.model.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosAgendaConsulta(
        Long id,
        @NotNull(message = "id del paciente no puede ser nulo") Long idPaciente,
        Long idMedico,
        @NotNull @Future LocalDateTime fecha
) {
}
