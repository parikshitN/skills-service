package com.demo.skills.domain.model

import java.util.UUID

data class Skill(
    val uuid: UUID = UUID.randomUUID(),
    val name: String,
    val domain: Domain
)
