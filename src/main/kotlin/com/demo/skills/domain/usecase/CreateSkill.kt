package com.demo.skills.domain.usecase

import com.demo.skills.model.Skill
import com.demo.skills.repository.SkillRepository

class CreateSkill(val skillRepository: SkillRepository) {
    operator fun invoke(skill: String, domain: String) {
        skillRepository.save(Skill(name = skill, domain = domain))
    }
}
