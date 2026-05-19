package CRUD.PacientesAPI.Kotlim.Infra.Validators

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import CRUD.PacientesAPI.Kotlim.Infra.Exceptions.BusinessException
import org.springframework.stereotype.Component

@Component
class ValidarMedicoAtivo : ValidacaoAgendamentoConsulta {
    override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
        if (!medico.ativa)
            throw BusinessException("Médico inativo não pode receber consultas")
    }
}