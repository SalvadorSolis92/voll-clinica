package med.voll.api.model.medico;

import med.voll.api.model.direccion.DireccionDTO;

public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento,
                                   DireccionDTO direccion) {
}
