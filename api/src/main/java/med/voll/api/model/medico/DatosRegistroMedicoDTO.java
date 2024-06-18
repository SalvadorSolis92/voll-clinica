package med.voll.api.model.medico;

import med.voll.api.model.direccion.DireccionDTO;

public record DatosRegistroMedicoDTO(
        String nombre,
        String email,
        String documento,
        EspecialidadDTO especialidad,
        DireccionDTO direccion
) {
}
