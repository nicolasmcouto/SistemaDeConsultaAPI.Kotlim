package CRUD.PacientesAPI.Kotlim.Service

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoUpdateDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toAgendamentoDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toEntity
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toupDTO
import CRUD.PacientesAPI.Kotlim.Domain.repository.MedicoRepository
import CRUD.PacientesAPI.Kotlim.Infra.Exceptions.ConflictException
import CRUD.PacientesAPI.Kotlim.Infra.Exceptions.ResourceNotFoundException
import org.springframework.stereotype.Service


@Service
class MedicoService(private val repository: MedicoRepository) {

    fun findAll(): List<MedicoDTO> { return  repository.findAll().map{ it.toAgendamentoDTO()} }

    fun findById(id: Long): MedicoDTO? {
        val findbyid = repository.findById(id)
        val response = findbyid.map {it.toAgendamentoDTO() }.orElse(null)
        return response
    }

    fun salvar(dto: MedicoDTO): MedicoDTO {
        var entity = dto.toEntity()
        if (repository.existsByCrm(dto.crm)){
            throw ConflictException("CRM ja cadastrada")
        }
        if (repository.existsByEmail(dto.email)){
            throw ConflictException("email ja cadastrada")
        }
        var save = repository.save(entity)
        return save.toAgendamentoDTO()
    }

    fun delete(id: Long) {
        if (!repository.existsById(id))
            throw ResourceNotFoundException("Médico não encontrado com id $id") // novo
        repository.deleteById(id)
    }

    fun alterar(id: Long, dto: MedicoUpdateDTO) : MedicoUpdateDTO {

        val medico : MedicoEntity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Médico não encontrado com id $id") }

        medico.nome = dto.nome ?: medico.nome
        medico.crm = dto.crm ?: medico.crm
        medico.email = dto.email ?: medico.email
        medico.especialidade = dto.especialidade ?: medico.especialidade
        medico.ativa = dto.ativa ?: medico.ativa

        val saved = repository.save(medico)
        return saved.toupDTO()
    }

    fun softDelete(id: Long) {
        val medico : MedicoEntity = repository.findById(id)
            . orElseThrow { ResourceNotFoundException("Médico nao encontrado com id $id") }

        medico.ativa = false

        repository.save(medico)
    }
}

