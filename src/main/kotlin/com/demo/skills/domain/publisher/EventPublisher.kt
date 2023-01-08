package com.demo.skills.domain.publisher

import java.util.UUID

interface EventPublisher {
    fun publish(skillEvent: SkillEvent)
}

data class SkillDeletedEvent(val uuid: UUID) : SkillEvent{
    override fun getRoutingKey(): String {
        return "skill.event.delete"
    }
}

interface SkillEvent {
    fun getRoutingKey(): String
}
