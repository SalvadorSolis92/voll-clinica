package med.voll.api.model.consulta.validaciones;

import med.voll.api.errores.ValidacionDeIntegridad;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendaConsulta datosAgendaConsulta){
        if (datosAgendaConsulta.idPaciente() != null){
            return;
        }

        var pacienteActivo = pacienteRepository.findIsActive(datosAgendaConsulta.idPaciente());

        if(!pacienteActivo){
            throw new ValidacionDeIntegridad("El paciente no esta activo en el sistema");
        }

    }
}
