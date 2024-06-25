package med.voll.api.service;

import med.voll.api.errores.ValidacionDeIntegridad;
import med.voll.api.model.consulta.Consulta;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import med.voll.api.model.medico.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public void agendar(DatosAgendaConsulta datosAgendaConsulta){

        if (pacienteRepository.findById(datosAgendaConsulta.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("No existe el paciente");
        }

        if (datosAgendaConsulta.idMedico() != null && medicoRepository.existsById(datosAgendaConsulta.idMedico())){
            throw new ValidacionDeIntegridad("No existe el medico");
        }
        
        var medico = seleccionarMedico(datosAgendaConsulta);
        var paciente = pacienteRepository.findById(datosAgendaConsulta.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datosAgendaConsulta.fecha());
        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosAgendaConsulta datosAgendaConsulta) {
        if(datosAgendaConsulta.idMedico() != null){
            return medicoRepository.getReferenceById(datosAgendaConsulta.idMedico());
        }

        if (datosAgendaConsulta.especialidad() == null){
            throw new ValidacionDeIntegridad("debe indicar una especialidad");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datosAgendaConsulta.especialidad(), datosAgendaConsulta.fecha());
    }
}
