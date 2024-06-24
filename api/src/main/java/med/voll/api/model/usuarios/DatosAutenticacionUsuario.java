package med.voll.api.model.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank(message = "login no puede ser nulo") String login,
        @NotBlank(message = "la clave no puede ser nula") String clave)
{
}
