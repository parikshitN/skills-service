package com.demo.skills.infrastructure.repository

import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.infrastructure.entities.SkillDocument
import io.kotlintest.shouldBe
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.test.context.ContextConfiguration
import kotlin.jvm.optionals.getOrNull

@SpringBootTest
@ContextConfiguration(initializers = [MongoDBContainerInitializer::class])
class SkillRepositoryTest(
    @Autowired private val skillRepository: SkillRepository,
    @Autowired private val mongoTemplate: MongoTemplate
) {
    @BeforeEach
    internal fun setUp() {
        mongoTemplate.remove(Query(), SkillDocument::class.java)
    }

    @Test
    fun `should save a new skill Java for domain Tech`() {
        val saved = skillRepository.save(Skill(name = "Java", domain = Domain.TECH))

        val document = mongoTemplate.findById(saved.uuid, SkillDocument::class.java)

        document?.uuid shouldBe saved.uuid
        document?.domain shouldBe saved.domain.value
        document?.name shouldBe saved.name
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `given a name should find a skill with that name`() {
        val skill = SkillDocument.from(Skill(name = "Market Research", domain = Domain.BUSINESS))
        mongoTemplate.save(skill)

        val actual = skillRepository.findByName("Market Research")

        actual.getOrNull()?.name shouldBe "Market Research"
        actual.getOrNull()?.domain?.value shouldBe "Business"
    }

    @Test
    fun `should get all the skills`() {
        addSkillsAsPrecondition()

        val actual = skillRepository.findAll()

        actual.size `should be equal to` 3
        actual.map { it.name } `should be equal to` listOf("Market Research", "Java", "Scrum Master")
    }

    @Test
    fun `should be able delete all the skills`() {
        addSkillsAsPrecondition()

        skillRepository.deleteAll()

        val actual = mongoTemplate.findAll(SkillDocument::class.java)
        actual.size `should be equal to` 0
    }

    private fun addSkillsAsPrecondition() {
        val skill1 = SkillDocument.from(Skill(name = "Market Research", domain = Domain.BUSINESS))
        val skill2 = SkillDocument.from(Skill(name = "Java", domain = Domain.TECH))
        val skill3 = SkillDocument.from(Skill(name = "Scrum Master", domain = Domain.LEADERSHIP))
        mongoTemplate.save(skill1)
        mongoTemplate.save(skill2)
        mongoTemplate.save(skill3)
    }
}
