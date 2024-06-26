package med.voll.api.model.consulta.validaciones;

import med.voll.api.errores.ValidacionDeIntegridad;
import med.voll.api.model.consulta.DatosAgendaConsulta;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas  {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendaConsulta datosAgendaConsulta){
        if (datosAgendaConsulta.idMedico() == null){
            return;
        }

        var medicoActivo = medicoRepository.findIsActive(datosAgendaConsulta.idMedico());

        if(!medicoActivo){
            throw new ValidacionDeIntegridad("El medico no esta activo en el sistema");
        }

    }
}
