package com.demo.skills.infrastructure.entities

import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("skills")
data class SkillDocument(
    @Id val uuid: UUID,
    val name: String,
    val domain : String) {

    companion object{
        fun from(skill: Skill) : SkillDocument {
            return SkillDocument(skill.uuid, skill.name, skill.domain.value)
        }
    }

    fun toSkill() : Skill {
        return Skill(uuid, name, Domain.from(domain))
    }
}
