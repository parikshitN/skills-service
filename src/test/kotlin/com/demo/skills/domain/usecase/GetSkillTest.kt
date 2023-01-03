package com.demo.skills.domain.usecase

import com.demo.skills.domain.exception.ApiException
import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill
import com.demo.skills.domain.repository.SkillRepository
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Optional
import java.util.UUID

class GetSkillTest {

    private val skillRepository = mockk<SkillRepository>(relaxed = true)
    private val getSkill = GetSkill(skillRepository)

    @Test
    fun `should get a skill for a given uuid`() {
        val skillId = UUID.randomUUID()
        every { skillRepository.findById(skillId) } returns Optional.of(Skill(skillId, "Java", Domain.TECH))

        val actual = getSkill(skillId)

        actual `should be equal to` SkillOutput(skillId, "Java", "Tech")
    }

    @Test
    fun `should throw API exception if skill not found`() {
        every { skillRepository.findById(any()) } returns Optional.empty()

        val error = Assertions.assertThrows(ApiException::class.java) { getSkill(UUID.randomUUID()) }

        error.message shouldBe "Skill doesn't exists"
    }
}
