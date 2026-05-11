package CRUD.PacientesAPI.Kotlim.Service

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteUpdateDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.UpdateoDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toEntity
import CRUD.PacientesAPI.Kotlim.Domain.repository.PacienteRepository
import org.springframework.data.repository.findByIdOrNull

import org.springframework.stereotype.Service
import java.lang.RuntimeException


@Service
class PacienteService(private val repository: PacienteRepository) {

    fun salvar(dto: PacienteDTO) : PacienteDTO{
        val entity = dto.toEntity()
         val salvo = repository.save(entity)

        return salvo.toDTO()
    }

    fun buscaPorId(id: Long): PacienteDTO?{
        val dtoPaciente =  repository.findByIdOrNull(id)
            ?.toDTO()
        return dtoPaciente
    }

    fun deletar(id : Long){
        repository.deleteById(id)
    }

    fun listar(): List<PacienteDTO> {
        var list = repository.findAll()
            .map { it.toDTO() }

        return list
    }

    fun atualiza(id: Long, dto: PacienteUpdateDTO) : PacienteUpdateDTO{
        var existente = repository.findById(id)
            .orElseThrow{RuntimeException("Paciente nao encontyrado")}

        existente.nome = dto.nome ?: existente.nome
        existente.email = dto.email ?: existente.email
        existente.dataDeNascimento = dto.dataDeNascimento ?: existente.dataDeNascimento
        var pacienteAtualizado = repository.save(existente)
        return pacienteAtualizado.UpdateoDTO()
    }
}