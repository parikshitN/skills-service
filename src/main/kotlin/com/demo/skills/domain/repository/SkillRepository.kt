package com.demo.skills.domain.repository

import com.demo.skills.domain.model.Skill
import java.util.Optional
import java.util.UUID

interface SkillRepository {

    fun save(skill: Skill): Skill

    fun findByName(name: String): Optional<Skill>
    fun findAll(): List<Skill>
    fun deleteAll()
    fun findById(skillId: UUID): Optional<Skill>
}
