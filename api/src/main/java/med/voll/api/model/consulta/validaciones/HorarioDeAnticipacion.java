package med.voll.api.model.consulta.validaciones;

import med.voll.api.errores.ValidacionDeIntegridad;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas {

    public void validar(DatosAgendaConsulta datosAgendaConsulta){
        var ahora = LocalDateTime.now();
        var horaConsulta = datosAgendaConsulta.fecha();

        var diferenciaDe30Min = Duration.between(ahora, horaConsulta).toMinutes() > 30;

        if (diferenciaDe30Min){
            new ValidacionDeIntegridad("Se debe solicitar la consulta con 30 min de anticipación");
        }
    }
}
