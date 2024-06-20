package med.voll.api.model.medico;

public record DatosListadoMedicos(
        String nombre,
        String especialidad,
        String documento,
        String email
) {

    public DatosListadoMedicos(Medico m){
        this(m.getNombre(), m.getEspecialidad().toString(), m.getDocumento(), m.getEmail());
    }
}
