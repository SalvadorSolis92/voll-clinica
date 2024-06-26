package med.voll.api.repository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.consulta.Consulta;
import med.voll.api.model.direccion.DireccionDTO;
import med.voll.api.model.medico.DatosRegistroMedicoDTO;
import med.voll.api.model.medico.Especialidad;
import med.voll.api.model.medico.Medico;
import med.voll.api.model.pacientes.DatosRegistroPaciente;
import med.voll.api.model.pacientes.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//para usar la base de datos para pruebas
@ActiveProfiles("test")//para usar la base de datos de prueba
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("deberia retornar null cuando el medico ya tenga una consulta programada")
    void seleccionarMedicoConEspecialidadEnFecha() {

        var proximoLunes10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico = registrarMedico("Doctor Simi", "simi@correo.com", "12334567", Especialidad.CARDIOLOGIA, "1233456987") ;
        var paciente = registrarPaciente("paciente test", "paciente@test.com", "1234568791", "100012");
        registrarConsulta(medico, paciente, proximoLunes10AM);//insertamos una consulta

        var medicoLibre = repository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA, proximoLunes10AM);//se busca si ya existe una consulta

        assertNull(medicoLibre);
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad, String telefono){
        var medico = new Medico(new DatosRegistroMedicoDTO(nombre, email, documento, especialidad, null, telefono));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String telefono, String documento){
        var paciente = new Paciente(new DatosRegistroPaciente(nombre, email, telefono, documento));
        entityManager.persist(paciente);
        return paciente;
    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha){
        entityManager.persist(new Consulta(null , medico, paciente, fecha));
    }
}