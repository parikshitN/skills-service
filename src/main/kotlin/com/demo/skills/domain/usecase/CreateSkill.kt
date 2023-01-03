package com.demo.skills.domain.usecase

import com.demo.skills.domain.exception.ApiException
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.input.SkillInput

class CreateSkill(val skillRepository: SkillRepository) {
    operator fun invoke(skillInput: SkillInput): SkillOutput {
        validate(skillInput)
        val skill = skillRepository.save(skillInput.toSkill())
        return SkillOutput(skill.uuid, skill.name, skill.domain.label)
    }

    private fun validate(skillInput: SkillInput) {
        if (skillRepository.findByName(skillInput.name).isPresent) {
            throw ApiException("Skill ${skillInput.name} already exists")
        }
    }
}
