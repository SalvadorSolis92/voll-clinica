package med.voll.api.repository;

import med.voll.api.model.medico.Especialidad;
import med.voll.api.model.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("""
            SELECT m FROM Medico m\s
            WHERE m.isActive = true\s
            AND m.especialidad = :especialidad\s
            AND m.id not in(
                SELECT c.medico.id FROM Consulta c\s
                WHERE\s
                c.data = :fecha
            )
            ORDER BY rand()
            LIMIT 1
           \s""")
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);
}
