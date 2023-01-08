package com.demo.skills.infrastructure

import com.demo.skills.domain.event.SkillEvent
import com.demo.skills.domain.event.publisher.EventPublisher
import org.springframework.amqp.rabbit.core.RabbitTemplate

class SkillsEventPublisher(private val rabbitTemplate: RabbitTemplate, val topicExchangeName: String) : EventPublisher {
    override fun publish(skillEvent: SkillEvent, routingKey: String) {
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, skillEvent) {
            it.messageProperties.setHeader("content_type", "application/json")
            it
        }
    }
}
