package med.voll.api.model.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.direccion.DireccionDTO;

public record DatosActualizarDTO(
        @NotNull Long id,
        String nombre,
        String documento,
        DireccionDTO direccion
) {
}
