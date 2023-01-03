package com.demo.skills.domain.usecase

import com.demo.skills.domain.repository.SkillRepository
import java.util.UUID

class DeleteSkill(private val skillRepository: SkillRepository) {

    operator fun invoke(uuid: UUID) {
        skillRepository.delete(uuid)
    }
}
