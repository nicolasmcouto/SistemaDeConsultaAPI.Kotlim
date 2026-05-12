package CRUD.PacientesAPI.Kotlim.Service

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteUpdateDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.UpdateoDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toAgendamentoDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toEntity
import CRUD.PacientesAPI.Kotlim.Domain.repository.PacienteRepository
import CRUD.PacientesAPI.Kotlim.Infra.ConflictException
import CRUD.PacientesAPI.Kotlim.Infra.ResourceNotFoundException
import org.springframework.data.repository.findByIdOrNull

import org.springframework.stereotype.Service
import java.lang.RuntimeException


@Service
class PacienteService(private val repository: PacienteRepository) {

    fun salvar(dto: PacienteDTO) : PacienteDTO{
        val entity = dto.toEntity()
        if(repository.existsByEmail(entity.email)){
            throw ConflictException("Email ja cadastrado")
        }
         val salvo = repository.save(entity)
        return salvo.toAgendamentoDTO()
    }

    fun buscaPorId(id: Long): PacienteDTO?{
        val dtoPaciente =  repository.findByIdOrNull(id)
            ?.toAgendamentoDTO()
        return dtoPaciente
    }

    fun deletar(id : Long){
        if(!repository.existsById(id)){
            throw ResourceNotFoundException("Id do paciente nao encontrado")
        }
        repository.deleteById(id)
    }

    fun listar(): List<PacienteDTO> {
        var list = repository.findAll()
            .map { it.toAgendamentoDTO() }

        return list
    }

    fun atualiza(id: Long, dto: PacienteUpdateDTO) : PacienteUpdateDTO{
        var existente = repository.findById(id)
            .orElseThrow{ResourceNotFoundException("Paciente nao encontyrado")}

        existente.nome = dto.nome ?: existente.nome
        existente.email = dto.email ?: existente.email
        existente.dataDeNascimento = dto.dataDeNascimento ?: existente.dataDeNascimento
        var pacienteAtualizado = repository.save(existente)
        return pacienteAtualizado.UpdateoDTO()
    }

    fun softDelete(id: Long) {
        val paciente : PacienteEntity = repository.findById(id)
            . orElseThrow { ResourceNotFoundException("Paciente nao encontrado com id $id") }

        paciente.ativa = false
        repository.save(paciente)
    }
}