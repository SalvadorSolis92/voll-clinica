package med.voll.api.model.medico;

import med.voll.api.model.direccion.Direccion;

public record DatosRegistroMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        Direccion direccion
) {
}
