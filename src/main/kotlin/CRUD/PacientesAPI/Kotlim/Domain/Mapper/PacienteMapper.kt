package CRUD.PacientesAPI.Kotlim.Domain.Mapper

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteUpdateDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity

fun PacienteEntity.toAgendamentoDTO() = PacienteDTO(
    id = this.id,
    nome = this.nome,
    email = this.email!!,
    dataDeNascimento = this.dataDeNascimento!!,
    ativa = this.ativa
)

fun PacienteDTO.toEntity() = PacienteEntity(
    id = this.id,
    nome = this.nome.trim(),
    email = this.email?.trim()?.lowercase(),
    dataDeNascimento = this.dataDeNascimento,
    ativa = this.ativa
)
fun PacienteEntity.UpdateoDTO() = PacienteUpdateDTO(
    nome = this.nome,
    email = this.email,
    dataDeNascimento = this.dataDeNascimento,

)