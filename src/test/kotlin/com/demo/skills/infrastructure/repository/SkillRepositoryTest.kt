package com.demo.skills.infrastructure.repository

import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.infrastructure.entities.SkillDocument
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(initializers = [MongoDBContainerInitializer::class])
class SkillRepositoryTest(
    @Autowired private val skillRepository: SkillRepository, @Autowired private val mongoTemplate: MongoTemplate
) {

    @Test
    fun `should save a new skill Java for domain Tech`() {
        val saved = skillRepository.save(Skill(name = "Java", domain = Domain.TECH))

        val document = mongoTemplate.findById(saved.uuid, SkillDocument::class.java)
        document?.uuid shouldBe saved.uuid
        document?.domain shouldBe saved.domain.value
        document?.name shouldBe  saved.name
    }
}
