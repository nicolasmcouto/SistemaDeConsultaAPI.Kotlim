package CRUD.PacientesAPI.Kotlim.Infra.Validators

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import CRUD.PacientesAPI.Kotlim.Infra.Exceptions.BusinessException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ValidarAntecedencia : ValidacaoAgendamentoConsulta {
    override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
        if (dto.data.isBefore(LocalDateTime.now().plusMinutes(30)))
            throw BusinessException("Consulta deve ser agendada com pelo menos 30 minutos de antecedência")
    }
}