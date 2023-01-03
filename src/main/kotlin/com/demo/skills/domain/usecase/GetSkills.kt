package com.demo.skills.domain.usecase

import com.demo.skills.domain.repository.SkillRepository

class GetSkills(private val skillsRepository: SkillRepository) {

    operator fun invoke(): List<SkillOutput> {
        return skillsRepository.findAll()
            .map { SkillOutput(it.uuid, it.name, it.domain.label) }
    }
}
