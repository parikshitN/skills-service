package com.demo.skills.domain.usecase

import com.demo.skills.domain.usecase.input.SkillInput
import com.demo.skills.repository.SkillRepository

class CreateSkill(val skillRepository: SkillRepository) {
    operator fun invoke(skillInput: SkillInput) {
        skillRepository.save(skillInput.toSkill())
    }
}
