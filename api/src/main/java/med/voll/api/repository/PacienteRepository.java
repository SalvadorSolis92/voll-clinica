package med.voll.api.repository;

import med.voll.api.model.pacientes.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("""
            SELECT p.activo FROM Paciente p WHERE p.id = :id
            """)
    Boolean findIsActive(Long id);
}
