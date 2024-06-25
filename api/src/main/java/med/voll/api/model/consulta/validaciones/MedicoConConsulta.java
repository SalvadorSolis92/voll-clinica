package med.voll.api.model.consulta.validaciones;

import med.voll.api.errores.ValidacionDeIntegridad;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MedicoConConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendaConsulta datosAgendaConsulta){
        if (datosAgendaConsulta.idMedico() == null){
            return;
        }

        var medicoConConsulta = consultaRepository.existsByMedicoIdAndData(datosAgendaConsulta.idMedico(), datosAgendaConsulta.fecha());

        if(medicoConConsulta){
            throw new ValidacionDeIntegridad("El medico ya tiene consulta agendada en ese horario");
        }
    }
}
