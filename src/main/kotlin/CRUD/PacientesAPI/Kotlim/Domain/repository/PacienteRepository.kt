package CRUD.PacientesAPI.Kotlim.Domain.repository

import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PacienteRepository : JpaRepository<PacienteEntity,Long> {



    fun findByAtivaTrue(): List<PacienteEntity>

    fun findByEmail(email: String): PacienteEntity?
    fun existsById(id: Long?): Boolean
    fun existsByEmail(email: String?): Boolean
}