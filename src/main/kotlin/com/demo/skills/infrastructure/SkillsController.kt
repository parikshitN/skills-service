package com.demo.skills.infrastructure

import com.demo.skills.domain.usecase.CreateSkill
import com.demo.skills.domain.usecase.SkillOutput
import com.demo.skills.domain.usecase.input.SkillInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/skills")
class SkillsController {

    @Autowired
    lateinit var createSkill: CreateSkill

    @PostMapping
    fun create(@RequestBody skillInput: SkillInput): SkillOutput {
        return createSkill(skillInput)
    }
}
