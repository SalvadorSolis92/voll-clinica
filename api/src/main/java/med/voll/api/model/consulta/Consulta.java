package med.voll.api.model.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.medico.Medico;
import med.voll.api.model.pacientes.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "motivo_cancelamiento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamiento motivoCancelamiento;

    public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime fecha, MotivoCancelamiento motivoCancelamiento) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.motivoCancelamiento = motivoCancelamiento;
    }

    public void cancelar(MotivoCancelamiento motivo) {
        this.motivoCancelamiento = motivo;
    }
}
