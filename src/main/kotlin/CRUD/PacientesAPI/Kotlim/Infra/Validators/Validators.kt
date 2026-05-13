package CRUD.PacientesAPI.Kotlim.Infra.Validators

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import CRUD.PacientesAPI.Kotlim.Domain.repository.ConsultaRepository
import CRUD.PacientesAPI.Kotlim.Infra.Exceptions.BusinessException
import org.springframework.stereotype.Component
import java.time.DayOfWeek
import java.time.LocalDateTime

@Component
class ValidarHorarioComercial : ValidacaoAgendamento {
    override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
        val fimDeSemana = dto.data.dayOfWeek == DayOfWeek.SATURDAY ||
                dto.data.dayOfWeek == DayOfWeek.SUNDAY
        val foraDoHorario = dto.data.hour < 7 || dto.data.hour >= 18

        if (fimDeSemana || foraDoHorario)
            throw BusinessException("Consultas só podem ser agendadas de segunda a sexta, das 07h às 18h")
    }

    @Component
    class ValidarAntecedencia : ValidacaoAgendamento {
        override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
            if (dto.data.isBefore(LocalDateTime.now().plusMinutes(30)))
                throw BusinessException("Consulta deve ser agendada com pelo menos 30 minutos de antecedência")
        }
    }

    @Component
    class ValidarMedicoAtivo : ValidacaoAgendamento {
        override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
            if (!medico.ativa)
                throw BusinessException("Médico inativo não pode receber consultas")
        }
    }
}

@Component
class ValidarPacienteAtivo : ValidacaoAgendamento {
    override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
        if (!paciente.ativa)
            throw BusinessException("Paciente inativo não pode agendar consultas")
    }
}

@Component
class ValidarConflitoDeMedico(private val consultaRepository: ConsultaRepository) : ValidacaoAgendamento {
    override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
        if (consultaRepository.existsByMedicoIdAndData(medico, dto.data))
            throw BusinessException("Médico já possui consulta nesse horário")
    }
}

@Component
class ValidarConflitoDePaciente(private val consultaRepository: ConsultaRepository) : ValidacaoAgendamento {
    override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
        if (consultaRepository.existsByPacienteIdAndData(paciente, dto.data))
            throw BusinessException("Paciente já possui consulta nesse horário")
    }
}
