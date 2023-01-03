package com.demo.skills.domain.usecase

import com.demo.skills.domain.repository.SkillRepository
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import java.util.UUID

class DeleteSkillTest {

    @Test
    fun `should delete the skill for given uuid`() {
        val skillRepository = mockk<SkillRepository>(relaxed = true)
        val deleteSkill = DeleteSkill(skillRepository)
        val skillId = UUID.randomUUID()

        deleteSkill(skillId)

        val arg = slot<UUID>()
        verify(exactly = 1) { skillRepository.delete(capture(arg)) }
        arg.captured `should be equal to` skillId
    }
}
