package CRUD.PacientesAPI.Kotlim.Service

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.ConsultaEntity
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toDTO
import CRUD.PacientesAPI.Kotlim.Domain.repository.ConsultaRepository
import CRUD.PacientesAPI.Kotlim.Domain.repository.MedicoRepository
import CRUD.PacientesAPI.Kotlim.Domain.repository.PacienteRepository
import org.springframework.stereotype.Service

@Service
class ConsultaService(
    private val consultaRepository: ConsultaRepository,
    private val medicoRepository: MedicoRepository,
    private val pacienteRepository: PacienteRepository
) {

    fun agendarConsulta(dto: AgendamentoConsultaDTO): AgendamentoConsultaDTO{

        if(!medicoRepository.existsById(dto.medicoId)){
            throw RuntimeException("Id do medico invalido")
        }

        if(!pacienteRepository.existsById(dto.pacienteId)){
            throw RuntimeException("Id do paciente invalido")
        }
        val medico = medicoRepository.findById(dto.medicoId).get()
        val paciente = pacienteRepository.findById(dto.pacienteId).get()
        var consulta = ConsultaEntity(dto.id,medico,paciente,dto.data)

        consultaRepository.save(consulta)

        return consulta.toDTO()

    }
}