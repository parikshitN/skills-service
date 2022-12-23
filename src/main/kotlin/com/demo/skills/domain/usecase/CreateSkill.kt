package com.demo.skills.domain.usecase

import com.demo.skills.domain.exception.ApiException
import com.demo.skills.domain.usecase.input.SkillInput
import com.demo.skills.domain.repository.SkillRepository

class CreateSkill(val skillRepository: SkillRepository) {
    operator fun invoke(skillInput: SkillInput) {
        validate(skillInput)
        skillRepository.save(skillInput.toSkill())
    }

    private fun validate(skillInput: SkillInput) {
        if (skillRepository.findByName(skillInput.name).isPresent) {
            throw ApiException("Skill ${skillInput.name} already exists")
        }
    }
}
