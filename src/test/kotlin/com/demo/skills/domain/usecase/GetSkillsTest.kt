package com.demo.skills.domain.usecase

import com.demo.skills.domain.model.Domain
import com.demo.skills.domain.model.Skill
import com.demo.skills.domain.repository.SkillRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import java.util.UUID

class GetSkillsTest {

    @Test
    fun `should get all skills`() {
        val skillRepository = mockk<SkillRepository>()
        val getSkills = GetSkills(skillRepository)
        val javaSkillId = UUID.randomUUID()
        val accountingSkillId = UUID.randomUUID()
        val kotlinSkillId = UUID.randomUUID()
        val scrumMasterSkillId = UUID.randomUUID()
        every { skillRepository.findAll() } returns listOf(
            Skill(javaSkillId, "Java", Domain.TECH),
            Skill(kotlinSkillId, "Kotlin", Domain.TECH),
            Skill(accountingSkillId, "Accounting", Domain.BUSINESS),
            Skill(scrumMasterSkillId, "Scrum Master", Domain.LEADERSHIP)
        )
        val allSKills = getSkills()

        val expected = listOf(
            SkillOutput(javaSkillId, "Java", "Tech"),
            SkillOutput(kotlinSkillId, "Kotlin", "Tech"),
            SkillOutput(accountingSkillId, "Accounting", "Business"),
            SkillOutput(scrumMasterSkillId, "Scrum Master", "Leadership"),
        )
        allSKills `should be equal to` expected
    }
}
