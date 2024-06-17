package med.voll.api.model.direccion;

public record Direccion(
        String calle,
        String distrito,
        String ciudad,
        String numero,
        String complemento) {
}
