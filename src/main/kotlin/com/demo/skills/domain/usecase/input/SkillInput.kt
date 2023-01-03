package com.demo.skills.domain.usecase.input

import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill
import java.util.UUID

data class SkillInput(val uuid: UUID?, val name: String, val domain: String) {
    fun toSkill(): Skill {
        return if (uuid != null) Skill(uuid = uuid, name = name, domain = Domain.from(domain))
        else Skill(name = name, domain = Domain.from(domain))
    }
}
