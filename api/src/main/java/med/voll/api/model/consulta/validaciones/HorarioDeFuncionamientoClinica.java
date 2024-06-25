package med.voll.api.model.consulta.validaciones;

import med.voll.api.errores.ValidacionDeIntegridad;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas {

    public void validar(DatosAgendaConsulta datosAgendaConsulta){
        //validacion de tiempos
        var domingo = DayOfWeek.SUNDAY.equals(datosAgendaConsulta.fecha());
        var antesDeApertura = datosAgendaConsulta.fecha().getHour() < 7;
        var despuesDeCierrre = datosAgendaConsulta.fecha().getHour() > 19;

        if(domingo || antesDeApertura || despuesDeCierrre){
            throw  new ValidacionDeIntegridad("El dia seleccionado es domingo o el horario esta fuera de el horario de atención");
        }
    }
}
