package com.demo.skills.infrastructure

import com.demo.skills.domain.usecase.CreateSkill
import com.demo.skills.domain.usecase.GetSkill
import com.demo.skills.domain.usecase.GetSkills
import com.demo.skills.domain.usecase.UpdateSkill
import com.demo.skills.domain.usecase.input.SkillInput
import com.demo.skills.domain.usecase.ouput.SkillOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/skills")
class SkillsController {

    @Autowired
    lateinit var createSkill: CreateSkill
    @Autowired
    lateinit var getSkills: GetSkills
    @Autowired
    lateinit var getSkill: GetSkill
    @Autowired
    lateinit var updateSkill: UpdateSkill

    @PostMapping
    fun create(@RequestBody skillInput: SkillInput): SkillOutput {
        return createSkill(skillInput)
    }

    @GetMapping
    fun getAllSkills(): List<SkillOutput> {
        return getSkills()
    }

    @GetMapping("/{skillId}")
    fun getSkillWith(@PathVariable skillId: UUID): SkillOutput {
        return getSkill(skillId)
    }

    @PutMapping
    fun updateSkills(@RequestBody skillInput: SkillInput): SkillOutput {
        return updateSkill(skillInput)
    }
}
