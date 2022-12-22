package com.demo.skills.model

import java.util.UUID

data class Skill(
    val uuid: UUID = UUID.randomUUID(),
    val name: String,
    val domain2: Domain
)
