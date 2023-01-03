package com.demo.skills.domain.usecase

import com.demo.skills.domain.exception.ApiException
import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.domain.usecase.input.SkillInput
import com.demo.skills.domain.usecase.ouput.SkillOutput
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Optional
import java.util.UUID

class UpdateSkillTest {
    private val skillRepository = mockk<SkillRepository>(relaxed = true)
    private val updateSkill = UpdateSkill(skillRepository)

    @Test
    fun `should update a given skill`() {
        val skillInput = SkillInput(UUID.randomUUID(), "Java 8", "Tech")
        every { skillRepository.save(skillInput.toSkill()) } returns skillInput.toSkill()

        val actual = updateSkill(skillInput)

        actual `should be equal to` SkillOutput(skillInput.uuid!!, "Java 8", "Tech")
    }

    @Test
    fun `should throw exception if updated name already exists`() {
        val skillInput = SkillInput(UUID.randomUUID(), "Java 8", "Tech")
        every { skillRepository.findByName("Java 8") } returns Optional.of(Skill(UUID.randomUUID(), "Java 8", Domain.TECH))

        val error = Assertions.assertThrows(ApiException::class.java) { updateSkill(skillInput) }

        error.message `should be equal to` "Skill with name Java 8 already exists"
    }
}
