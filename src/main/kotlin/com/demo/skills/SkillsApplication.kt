package com.demo.skills

import com.demo.skills.domain.publisher.EventPublisher
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.CreateSkill
import com.demo.skills.domain.usecase.DeleteSkill
import com.demo.skills.domain.usecase.GetSkill
import com.demo.skills.domain.usecase.GetSkills
import com.demo.skills.domain.usecase.UpdateSkill
import com.demo.skills.infrastructure.SkillsEventPublisher
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@SpringBootApplication
class SkillsApplication

fun main(args: Array<String>) {
    runApplication<SkillsApplication>(*args)
}

@Configuration
@EnableMongoRepositories
class ApplicationConfiguration {

    @Bean
    fun createSkill(skillRepository: SkillRepository): CreateSkill {
        return CreateSkill(skillRepository)
    }

    @Bean
    fun getSkills(skillRepository: SkillRepository): GetSkills {
        return GetSkills(skillRepository)
    }

    @Bean
    fun getSkill(skillRepository: SkillRepository): GetSkill {
        return GetSkill(skillRepository)
    }

    @Bean
    fun updateSkill(skillRepository: SkillRepository): UpdateSkill {
        return UpdateSkill(skillRepository)
    }

    @Bean
    fun deleteSkill(skillRepository: SkillRepository, eventPublisher: EventPublisher): DeleteSkill {
        return DeleteSkill(skillRepository, eventPublisher)
    }

    @Bean
    fun queue(): Queue {
        return Queue("skill-management", false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange("skill-events")
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("skill.event.*")
    }

    @Bean
    fun eventPublisher(rabbitTemplate: RabbitTemplate): EventPublisher {
        return SkillsEventPublisher(rabbitTemplate, "skill-events")
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory, messageConverter: MessageConverter): RabbitTemplate? {
        val rabbitTemplate = RabbitTemplate()
        rabbitTemplate.connectionFactory = connectionFactory
        rabbitTemplate.messageConverter = messageConverter
        return rabbitTemplate
    }

    @Bean
    fun messageConverter(): MessageConverter? {
        return Jackson2JsonMessageConverter()
    }

}
