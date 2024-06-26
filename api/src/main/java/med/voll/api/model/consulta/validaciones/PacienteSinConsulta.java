package med.voll.api.model.consulta.validaciones;

import med.voll.api.errores.ValidacionDeIntegridad;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendaConsulta datosAgendaConsulta){
        var primerHorario = datosAgendaConsulta.fecha().withHour(7);
        var ultimaHorario = datosAgendaConsulta.fecha().withHour(18);

        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndFechaBetween(datosAgendaConsulta.idPaciente(), primerHorario, ultimaHorario);

        if(pacienteConConsulta){
            throw new ValidacionDeIntegridad("El paciente ya tiene consulta agendada en ese horario");
        }
    }
}
