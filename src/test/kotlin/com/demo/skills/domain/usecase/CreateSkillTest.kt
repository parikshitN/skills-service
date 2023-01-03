package com.demo.skills.domain.usecase

import com.demo.skills.domain.exception.ApiException
import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.input.SkillInput
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Optional
import java.util.UUID

class CreateSkillTest {
    private val skillRepository = mockk<SkillRepository>(relaxed = true)
    private val createSkill = CreateSkill(skillRepository)

    @Test
    fun `given skill Java for domain Tech the skill should get added`() {
        createSkill(SkillInput(UUID.randomUUID(), "Java", "Tech"))

        val skill = slot<Skill>()
        verify(exactly = 1) {
            skillRepository.save(capture(skill))
        }
        skill.captured.name shouldBe "Java"
        skill.captured.domain shouldBe Domain.TECH
    }

    @Test
    fun `given skill name Java which already exists and domain Tech the skill should not get added`() {
        every { skillRepository.findByName("Java") } returns
            Optional.of(Skill(name = "Java", domain = Domain.from("Tech")))

        val error = Assertions.assertThrows(ApiException::class.java) {
            createSkill(
                SkillInput(
                    UUID.randomUUID(),
                    "Java",
                    "Tech"
                )
            )
        }

        error.message shouldBe "Skill Java already exists"
    }
}
