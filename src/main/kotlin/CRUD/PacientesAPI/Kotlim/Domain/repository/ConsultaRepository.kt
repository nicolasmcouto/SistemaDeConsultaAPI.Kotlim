package CRUD.PacientesAPI.Kotlim.Domain.repository

import CRUD.PacientesAPI.Kotlim.Domain.Entity.ConsultaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ConsultaRepository: JpaRepository<ConsultaEntity, Long> {
}