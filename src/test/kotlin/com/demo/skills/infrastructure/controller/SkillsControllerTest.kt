package com.demo.skills.infrastructure.controller

import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.ouput.SkillOutput
import com.demo.skills.domain.usecase.input.SkillInput
import com.demo.skills.infrastructure.repository.MongoDBContainerInitializer
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
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

    @BeforeEach
    internal fun setUp() {
        skillRepository.deleteAll()
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `should create skill`() {
        val input = SkillInput("Java", "Tech")

        val response = restTemplate.postForEntity("http://localhost:$port/api/skills", input, SkillOutput::class.java)

        response.statusCode `should be equal to` HttpStatus.OK
        val skillOutput = response.body
        skillOutput?.name `should be equal to` "Java"
        skillOutput?.domain `should be equal to` "Tech"

        val saved = skillRepository.findByName("Java")
        saved.getOrNull()?.name `should be equal to` "Java"
        saved.getOrNull()?.domain?.label `should be equal to` "Tech"
    }

    @Test
    fun `should get all skills`() {
        skillRepository.save(Skill(name = "Java", domain = Domain.TECH))
        skillRepository.save(Skill(name = "Kotlin", domain = Domain.TECH))
        skillRepository.save(Skill(name = "Accounting", domain = Domain.BUSINESS))
        val type = object : ParameterizedTypeReference<List<SkillOutput>>() {}
        val requestEntity = RequestEntity.method(HttpMethod.GET, "http://localhost:$port/api/skills").build()

        val response = restTemplate.exchange(requestEntity, type)

        response.statusCode `should be equal to` HttpStatus.OK
        response.body?.map { it.name } `should be equal to` listOf("Java", "Kotlin", "Accounting")
    }

    @Test
    fun `should get a skill for a given skill uuid`() {
        val skill = Skill(name = "Java", domain = Domain.TECH)
        skillRepository.save(skill)

        val response = restTemplate.getForEntity("http://localhost:$port/api/skills/${skill.uuid}", SkillOutput::class.java)

        response.statusCode `should be equal to` HttpStatus.OK
        response.body `should be equal to` SkillOutput(skill.uuid, skill.name, skill.domain.label)
    }
}
