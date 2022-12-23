package com.demo.skills.infrastructure.repository

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

class MongoDBContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    companion object {
        private val image =
            DockerImageName.parse("mongo:4.0.10")
        private val mongoDB = MongoDBContainer(image)
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        mongoDB.start()
        TestPropertyValues.of(
            "spring.datasource.url=${mongoDB.connectionString}",
        ).applyTo(applicationContext.environment)
    }
}
