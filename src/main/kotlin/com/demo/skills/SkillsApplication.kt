package com.demo.skills

import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.CreateSkill
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
}
