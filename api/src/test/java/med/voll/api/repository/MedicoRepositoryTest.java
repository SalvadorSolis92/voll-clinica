package med.voll.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//para usar la base de datos para pruebas
@ActiveProfiles("test")//para usar la base de datos de prueba
class MedicoRepositoryTest {

    @Test
    void seleccionarMedicoConEspecialidadEnFecha() {
    }
}