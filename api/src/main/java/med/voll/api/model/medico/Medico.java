package med.voll.api.model.medico;


import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
    private String telefono;
    @Column(name = "activo")
    private boolean isActive;


    public Medico(DatosRegistroMedicoDTO medico) {
        this.nombre = medico.nombre();
        this.email = medico.email();
        this.telefono = medico.telefono();
        this.documento = medico.documento();
        this.especialidad = medico.especialidad();
        this.direccion = new Direccion(medico.direccion());
        this.isActive = true;
    }

    public void desarctivarMedico() {
        this.isActive = false;
    }

    public void actualizarDatosMedico(DatosActualizarDTO datos){
        if (datos.nombre()!= null){
            this.nombre = datos.nombre();
        }

        if (datos.documento() != null ){
            this.documento = datos.documento();
        }
        this.direccion = this.direccion.actualizarDireccion(datos.direccion());
    }

}
