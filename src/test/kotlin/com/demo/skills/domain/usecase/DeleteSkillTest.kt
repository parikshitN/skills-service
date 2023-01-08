package com.demo.skills.domain.usecase

import com.demo.skills.domain.publisher.EventPublisher
import com.demo.skills.domain.publisher.SkillDeletedEvent
import com.demo.skills.domain.publisher.SkillEvent
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
        val publisher = mockk<EventPublisher>(relaxed = true)
        val deleteSkill = DeleteSkill(skillRepository, publisher)
        val skillId = UUID.randomUUID()

        deleteSkill(skillId)

        val uuidArg = slot<UUID>()
        verify(exactly = 1) { skillRepository.delete(capture(uuidArg)) }
        uuidArg.captured `should be equal to` skillId
        val skillEvent = slot<SkillEvent>()
        verify(exactly = 1) { publisher.publish(capture(skillEvent)) }
        skillEvent.captured `should be equal to` SkillDeletedEvent(skillId)
    }
}
