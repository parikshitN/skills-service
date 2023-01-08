package com.demo.skills.domain.event

import java.util.UUID

data class SkillDeletedEvent(val uuid: UUID) : SkillEvent

interface SkillEvent
