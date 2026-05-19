package CRUD.PacientesAPI.Kotlim.Infra.Validators

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import CRUD.PacientesAPI.Kotlim.Infra.Exceptions.BusinessException
import org.springframework.stereotype.Component
import java.time.DayOfWeek

@Component
class ValidarHorarioComercial : ValidacaoAgendamentoConsulta {
    override fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity) {
        val fimDeSemana = dto.data.dayOfWeek == DayOfWeek.SATURDAY ||
                dto.data.dayOfWeek == DayOfWeek.SUNDAY
        val foraDoHorario = dto.data.hour < 7 || dto.data.hour >= 18

        if (fimDeSemana || foraDoHorario)
            throw BusinessException("Consultas só podem ser agendadas de segunda a sexta, das 07h às 18h")
    }
}