package CRUD.PacientesAPI.Kotlim.Service

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.ConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Factory.ConsultaFactory
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toAgendamentoDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toDTO
import CRUD.PacientesAPI.Kotlim.Domain.repository.ConsultaRepository
import CRUD.PacientesAPI.Kotlim.Domain.repository.MedicoRepository
import CRUD.PacientesAPI.Kotlim.Domain.repository.PacienteRepository
import CRUD.PacientesAPI.Kotlim.Infra.Exceptions.ResourceNotFoundException
import CRUD.PacientesAPI.Kotlim.Infra.Validators.ValidacaoAgendamentoConsulta
import org.springframework.stereotype.Service

@Service
class ConsultaService(
    private val consultaRepository: ConsultaRepository,
    private val medicoRepository: MedicoRepository,
    private val pacienteRepository: PacienteRepository,
    private val validacoes: List<ValidacaoAgendamentoConsulta>
) {
    fun agendarConsulta(dto: AgendamentoConsultaDTO): AgendamentoConsultaDTO {

        val medico = medicoRepository.findById(dto.medicoId)
            .orElseThrow { ResourceNotFoundException("Médico não encontrado com id ${dto.medicoId}") }

        val paciente = pacienteRepository.findById(dto.pacienteId)
            .orElseThrow { ResourceNotFoundException("Paciente não encontrado com id ${dto.pacienteId}") }

        validacoes.forEach { it.validar(dto, medico, paciente) }

        val consulta = ConsultaFactory.create(dto, medico, paciente)
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