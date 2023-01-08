package com.demo.skills.infrastructure

import com.demo.skills.domain.publisher.EventPublisher
import com.demo.skills.domain.publisher.SkillEvent
import org.springframework.amqp.rabbit.core.RabbitTemplate

class SkillsEventPublisher(private val rabbitTemplate: RabbitTemplate, val topicExchangeName: String) : EventPublisher {
    override fun publish(skillEvent: SkillEvent) {
        rabbitTemplate.convertAndSend(topicExchangeName, skillEvent.getRoutingKey(), skillEvent)
    }

}
