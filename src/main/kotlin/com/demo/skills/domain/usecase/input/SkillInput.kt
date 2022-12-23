package com.demo.skills.domain.usecase.input

import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill

data class SkillInput(val name: String, val domain: String) {
    fun toSkill(): Skill {
        return Skill(name = name, domain = Domain.from(domain))
    }
}
