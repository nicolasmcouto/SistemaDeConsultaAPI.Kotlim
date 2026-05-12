package CRUD.PacientesAPI.Kotlim.Domain.Mapper

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.MedicoUpdateDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity

fun MedicoEntity.toAgendamentoDTO() = MedicoDTO(
    id = this.id,
    nome = this.nome,
    crm = this.crm,
    email = this.email ,
    especialidade = this.especialidade,
    ativa = this.ativa
)

fun List<MedicoEntity>.toAgendamentoDTO(): List<MedicoDTO> = this.map { it.toAgendamentoDTO() }

fun MedicoDTO.toEntity() = MedicoEntity(
    id = this.id,
    nome = this.nome,
    crm = this.crm,
    email = this.email,
    especialidade = this.especialidade,
    ativa = this.ativa
)

fun MedicoEntity.toupDTO() = MedicoUpdateDTO(
    id = this.id,
    nome = this.nome,
    crm = this.crm,
    email = this.email,
    especialidade = this.especialidade,
    ativa = this.ativa
)