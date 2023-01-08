package com.demo.skills.domain.usecase

import com.demo.skills.domain.event.SkillDeletedEvent
import com.demo.skills.domain.event.publisher.EventPublisher
import com.demo.skills.domain.repository.SkillRepository
import java.util.UUID

class DeleteSkill(private val skillRepository: SkillRepository, private val eventPublisher: EventPublisher) {

    operator fun invoke(uuid: UUID) {
        skillRepository.delete(uuid)
        eventPublisher.publish(SkillDeletedEvent(uuid), "skill.event.delete")
    }
}
