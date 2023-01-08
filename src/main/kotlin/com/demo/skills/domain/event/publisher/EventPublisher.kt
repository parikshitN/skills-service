package com.demo.skills.domain.event.publisher

import com.demo.skills.domain.event.SkillEvent

interface EventPublisher {
    fun publish(skillEvent: SkillEvent, channel: String)
}
