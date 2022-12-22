package com.demo.skills

import com.demo.skills.domain.usecase.CreateSkill
import com.demo.skills.domain.usecase.input.SkillInput
import com.demo.skills.model.Skill
import com.demo.skills.repository.SkillRepository
import io.kotlintest.shouldBe
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test

class CreateSkillTest {
    @Test
    fun `given skill Java for domain Tech the skill should get added`() {
        val skillRepository = mockk<SkillRepository>(relaxed = true)
        val createSkill = CreateSkill(skillRepository)
        val skill = slot<Skill>()
        createSkill(SkillInput("Java", "Tech"))

        verify(exactly =1){
            skillRepository.save(capture(skill))
        }

        skill.captured.name shouldBe  "Java"
        skill.captured.domain shouldBe  "Tech"
    }
}
