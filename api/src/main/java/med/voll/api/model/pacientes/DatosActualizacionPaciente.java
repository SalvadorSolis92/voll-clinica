package med.voll.api.model.pacientes;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.direccion.DireccionDTO;

public record DatosActualizacionPaciente(
        @NotNull
        Long id,
        String nombre,
        String telefono) {
}