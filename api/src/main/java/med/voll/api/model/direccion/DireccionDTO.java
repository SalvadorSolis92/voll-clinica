package med.voll.api.model.direccion;

import jakarta.validation.constraints.NotBlank;

public record DireccionDTO(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String complemento) {
}
