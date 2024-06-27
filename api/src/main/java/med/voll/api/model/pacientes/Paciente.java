package med.voll.api.model.pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private String calle;
    private String distrito;
    private String complemento;
    private String numero;
    private String ciudad;
    private String telefono;
    private boolean activo;

    public Paciente(DatosRegistroPaciente datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
    }

    public Paciente(String nombre, String email, String telefono, String documento, String calle, String distrito, String complemento, String numero, String ciudad) {
        this.activo = true;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.documento = documento;
        this.calle = calle;
        this.distrito = distrito;
        this.complemento = complemento;
        this. numero = numero;
        this.ciudad = ciudad;
    }

    public void actualizarInformacoes(DatosActualizacionPaciente datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.telefono() != null) {
            this.telefono = datos.telefono();
        }

    }

    public void eliminar() {
        this.activo = false;
    }
}

