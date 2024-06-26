package med.voll.api.model.pacientes;

public record DatosDetallesPaciente(Long id, String nombre, String email, String documento, String telefono) {

    public DatosDetallesPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento(), paciente.getTelefono());
    }
}