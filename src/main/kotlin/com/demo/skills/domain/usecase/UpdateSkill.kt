package com.demo.skills.domain.usecase

import com.demo.skills.domain.exception.ApiException
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.input.SkillInput
import com.demo.skills.domain.usecase.ouput.SkillOutput

class UpdateSkill(private val skillRepository: SkillRepository) {

    operator fun invoke(skillInput: SkillInput): SkillOutput {
        skillRepository.findByName(skillInput.name).ifPresent {
            if (it.uuid != skillInput.uuid) {
                throw ApiException("Skill with name ${skillInput.name} already exists")
            }
        }
        val updated = skillRepository.save(skillInput.toSkill())
        return SkillOutput(updated.uuid, updated.name, updated.domain.label)
    }
}
