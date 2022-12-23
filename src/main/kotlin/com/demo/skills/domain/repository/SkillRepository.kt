package com.demo.skills.domain.repository

import com.demo.skills.domain.model.Skill
import java.util.*
 interface SkillRepository {

    fun save(skill: Skill) : Skill

    fun findByName(name: String) : Optional<Skill>

}
