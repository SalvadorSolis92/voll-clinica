package med.voll.api.model.medico;


import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.direccion.Direccion;
@Table(name = "medicos")
@Entity(name = "Medico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;


    public Medico(DatosRegistroMedicoDTO medico) {
        this.nombre = medico.nombre();
        this.email = medico.email();
        this.documento = medico.documento();
        this.especialidad = medico.especialidad();
        this.direccion = new Direccion(medico.direccion());
    }
}
