package med.voll.api.repository;

import med.voll.api.model.medico.Especialidad;
import med.voll.api.model.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("""
            select m from Medico m
            where m.isActive = true 
            and
            m.especialidad=:especialidad 
            and
            m.id not in(  
                select c.medico.id from Consulta c
                where
                c.fecha=:fecha
            )
            order by rand()
            limit 1
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);

    @Query("""
            SELECT m.isActive FROM Medico m WHERE m.id = :idMedico
            """)
    Boolean findIsActive(Long idMedico);

}
