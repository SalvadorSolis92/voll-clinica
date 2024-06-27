package med.voll.api.repository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.consulta.Consulta;
import med.voll.api.model.direccion.Direccion;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//para usar la base de datos para pruebas
@ActiveProfiles("test")//para usar la base de datos de prueba
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("deberia retornar nulo cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario1() {

        //given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico=registrarMedico("Jose","j@mail.com","123456",Especialidad.CARDIOLOGIA);
        var paciente= registrarPaciente("antonio","a@mail.com","654321", "1005", "calle", "distrito", "comple", "1234567898", "CDMX");
        registrarConsulta(medico,paciente,proximoLunes10H);

        //when
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA,proximoLunes10H);

        //then
        assertThat(medicoLibre).isNull();
    }


    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        entityManager.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String telefono, String documento, String calle, String distrito, String complemento, String numero, String ciudad) {
        var paciente = new Paciente(nombre, email, documento, telefono, documento, calle, distrito, numero, ciudad );
        entityManager.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedicoDTO datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedicoDTO(
                nombre,
                email,
                documento,
                especialidad,
                datosDireccion(),
                "61999999999"
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                "61999999999",
                documento
        );
    }

    private DireccionDTO datosDireccion() {
        return new DireccionDTO(
                " loca",
                "azul",
                "acapulpo",
                "321",
                "12"
        );
    }
}