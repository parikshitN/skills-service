package com.demo.skills

import com.demo.skills.domain.usecase.CreateSkill
import com.demo.skills.model.Skill
import com.demo.skills.repository.SkillRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class CreateSkillTest {
    @Test
    internal fun `given skill Java for domain Tech the skill should get added`() {
        val skillRepository = mockk<SkillRepository>()
        val createSkill = CreateSkill()

        createSkill("Java", "Tech")

        verify(exactly =1){
            skillRepository.save(Skill(name = "Java", domain = "Tech"))
        }
    }
}
