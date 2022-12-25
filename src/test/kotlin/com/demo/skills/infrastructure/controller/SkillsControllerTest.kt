package com.demo.skills.infrastructure.controller

import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.SkillOutput
import com.demo.skills.domain.usecase.input.SkillInput
import com.demo.skills.infrastructure.repository.MongoDBContainerInitializer
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import kotlin.jvm.optionals.getOrNull

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [MongoDBContainerInitializer::class])
class SkillsControllerTest {
    @Value(value = "\${local.server.port}")
    private val port = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var skillRepository: SkillRepository

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `should create skill`() {
        val input = SkillInput("Java", "Tech")

        val response = restTemplate.postForEntity("http://localhost:$port/api/skills", input, SkillOutput::class.java)

        response.statusCode shouldBe HttpStatus.OK
        val skillOutput = response.body
        skillOutput?.name shouldBe "Java"
        skillOutput?.domain shouldBe "Tech"

        val saved = skillRepository.findByName("Java")
        saved.getOrNull()?.name shouldBe "Java"
        saved.getOrNull()?.domain?.value shouldBe "Tech"
    }
}
