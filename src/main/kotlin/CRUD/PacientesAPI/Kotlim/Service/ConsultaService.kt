package CRUD.PacientesAPI.Kotlim.Service

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.ConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.ConsultaEntity
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toAgendamentoDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toDTO
import CRUD.PacientesAPI.Kotlim.Domain.repository.ConsultaRepository
import CRUD.PacientesAPI.Kotlim.Domain.repository.MedicoRepository
import CRUD.PacientesAPI.Kotlim.Domain.repository.PacienteRepository
import CRUD.PacientesAPI.Kotlim.Infra.BusinessException
import CRUD.PacientesAPI.Kotlim.Infra.ConflictException
import CRUD.PacientesAPI.Kotlim.Infra.ResourceNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDateTime

@Service
class ConsultaService(
    private val consultaRepository: ConsultaRepository,
    private val medicoRepository: MedicoRepository,
    private val pacienteRepository: PacienteRepository
) {
    fun agendarConsulta(dto: AgendamentoConsultaDTO): AgendamentoConsultaDTO {

        val fimDeSemana = dto.data.dayOfWeek == DayOfWeek.SATURDAY ||
                dto.data.dayOfWeek == DayOfWeek.SUNDAY
        val foraDoHorarioComercial = dto.data.hour < 7 || dto.data.hour >= 18

        if (fimDeSemana || foraDoHorarioComercial)
            throw BusinessException("Consultas só podem ser agendadas de segunda a sexta, das 07h às 18h")

        if (dto.data.isBefore(LocalDateTime.now().plusMinutes(30)))
            throw BusinessException("Consulta deve ser agendada com pelo menos 30 minutos de antecedência")

        val medico = medicoRepository.findById(dto.medicoId)
            .orElseThrow { ResourceNotFoundException("Médico não encontrado com id ${dto.medicoId}") }

        val paciente = pacienteRepository.findById(dto.pacienteId)
            .orElseThrow { ResourceNotFoundException("Paciente não encontrado com id ${dto.pacienteId}") }

        if (!medico.ativa)
            throw BusinessException("Médico inativo não pode receber consultas")

        if (!paciente.ativa)
            throw BusinessException("Paciente inativo não pode agendar consultas")

        if (consultaRepository.existsByMedicoIdAndData(medico, dto.data))
            throw BusinessException("Médico já possui consulta nesse horário")

        if (consultaRepository.existsByPacienteIdAndData(paciente, dto.data))
            throw BusinessException("Paciente já possui consulta nesse horário")

        val consulta = ConsultaEntity(dto.id, medico, paciente, dto.data)
        consultaRepository.save(consulta)

        return consulta.toAgendamentoDTO()
    }
    fun findAll(): List<ConsultaDTO> { return consultaRepository.findAll().map { it.toDTO() } }

    fun findById(id: Long): ConsultaDTO {
        return consultaRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Consulta não encontrada com id $id") }
            .toDTO()
    }

    fun delete(id: Long) {
        if (!consultaRepository.existsById(id))
            throw ResourceNotFoundException("Consulta não encontrada com id $id")
        consultaRepository.deleteById(id)
    }


}