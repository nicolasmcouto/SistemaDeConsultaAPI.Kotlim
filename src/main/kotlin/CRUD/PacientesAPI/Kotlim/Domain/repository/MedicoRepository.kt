package CRUD.PacientesAPI.Kotlim.Domain.repository

import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MedicoRepository : JpaRepository<MedicoEntity, Long> {

    fun findByAtivaTrue(): List<MedicoEntity>

    fun findByEmail(email: String): MedicoEntity?
    abstract fun existsById(id: Long?): Boolean
    fun existsByCrm(crm: String?): Boolean
    abstract fun existsByEmail(email: String): Boolean

}