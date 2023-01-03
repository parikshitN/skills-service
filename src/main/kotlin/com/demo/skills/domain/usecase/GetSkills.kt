package com.demo.skills.domain.usecase

import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.ouput.SkillOutput

class GetSkills(private val skillsRepository: SkillRepository) {

    operator fun invoke(): List<SkillOutput> {
        return skillsRepository.findAll()
            .map { SkillOutput(it.uuid, it.name, it.domain.label) }
    }
}
