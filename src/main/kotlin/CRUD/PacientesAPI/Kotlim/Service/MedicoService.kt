package CRUD.PacientesAPI.Kotlim.Service

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoUpdateDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toDTO
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toEntity
import CRUD.PacientesAPI.Kotlim.Domain.Mapper.toupDTO
import CRUD.PacientesAPI.Kotlim.Domain.repository.MedicoRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import kotlin.jvm.optionals.getOrNull


@Service
class MedicoService(private val repository: MedicoRepository) {

    fun findAll(): List<MedicoDTO> {
      var findAll =  repository.findAll()
                     .map{ it.toDTO()}
        return findAll
    }

    fun findById(id: Long): MedicoDTO? {
        val findbyid = repository.findById(id)
        val response = findbyid.map {it.toDTO() }.orElse(null)
        return response
    }

    fun salvar(dto: MedicoDTO): MedicoDTO {
        var entity = dto.toEntity()
        if (repository.existsByCrm(dto.crm)){
            throw RuntimeException("CRM ja cadastrada")
        }
        if (repository.existsByEmail(dto.email)){
            throw RuntimeException("email ja cadastrada")
        }
        var save = repository.save(entity)
        return save.toDTO()
    }

    fun delete(id: Long){
      return  repository.deleteById(id)
    }

    fun alterar(id: Long, dto: MedicoUpdateDTO) : MedicoUpdateDTO {

        val medico : MedicoEntity = repository.findById(id)
            .orElseThrow{RuntimeException("Medico nao encontrado")}

        medico.nome = dto.nome ?: medico.nome
        medico.crm = dto.crm ?: medico.crm
        medico.email = dto.email ?: medico.email
        medico.especialidade = dto.especialidade ?: medico.especialidade
        medico.ativa = dto.ativa ?: medico.ativa

        val saved = repository.save(medico)
        return saved.toupDTO()




    }
}

