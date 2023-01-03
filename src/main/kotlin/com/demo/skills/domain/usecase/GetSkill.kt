package com.demo.skills.domain.usecase

import com.demo.skills.domain.exception.ApiException
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.ouput.SkillOutput
import java.util.UUID

class GetSkill(private val skillRepository: SkillRepository) {
    operator fun invoke(skillId: UUID): SkillOutput {
        val skill = skillRepository.findById(skillId).orElseThrow { ApiException("Skill doesn't exists") }
        return SkillOutput(skill.uuid, skill.name, skill.domain.label)
    }
}
