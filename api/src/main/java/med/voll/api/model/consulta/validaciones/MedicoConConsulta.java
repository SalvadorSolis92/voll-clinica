package med.voll.api.model.consulta.validaciones;

import med.voll.api.errores.ValidacionDeIntegridad;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendaConsulta datosAgendaConsulta){
        if (datosAgendaConsulta.idMedico() == null){
            return;
        }

        var medicoConConsulta = consultaRepository.existsByMedicoIdAndFecha(datosAgendaConsulta.idMedico(), datosAgendaConsulta.fecha());

        if(medicoConConsulta){
            throw new ValidacionDeIntegridad("El medico ya tiene consulta agendada en ese horario");
        }
    }
}
