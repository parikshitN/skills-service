package com.demo.skills

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
class SkillsApplication

fun main(args: Array<String>) {
    runApplication<SkillsApplication>(*args)
}

@Configuration
@EnableMongoRepositories
class ApplicationConfiguration
