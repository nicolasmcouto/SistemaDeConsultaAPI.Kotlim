package CRUD.PacientesAPI.Kotlim.Infra.Validators

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import CRUD.PacientesAPI.Kotlim.Domain.repository.ConsultaRepository
import CRUD.PacientesAPI.Kotlim.Infra.Exceptions.BusinessException
import org.springframework.stereotype.Component

@Component
class ValidarConflitoDeMedico(private val consultaRepository: ConsultaRepository) : ValidacaoAgendamentoConsulta {
    override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
        if (consultaRepository.existsByMedicoIdAndData(medico, dto.data))
            throw BusinessException("Médico já possui consulta nesse horário")
    }
}