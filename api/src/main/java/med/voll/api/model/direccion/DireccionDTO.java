package med.voll.api.model.direccion;

public record DireccionDTO(
        String calle,
        String distrito,
        String ciudad,
        String numero,
        String complemento) {
}
