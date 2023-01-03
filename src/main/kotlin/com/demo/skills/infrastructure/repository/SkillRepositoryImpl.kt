package com.demo.skills.infrastructure.repository

import com.demo.skills.domain.model.Skill
import com.demo.skills.domain.repository.SkillRepository
import com.demo.skills.infrastructure.entities.SkillDocument
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
class SkillRepositoryImpl(@Autowired private val skillMongoRepository: SkillMongoRepository) : SkillRepository {
    override fun save(skill: Skill): Skill {
        return skillMongoRepository.save(SkillDocument.from(skill))
            .toSkill()
    }

    override fun findByName(name: String): Optional<Skill> {
        val skillDocument = skillMongoRepository.findByName(name)
        return if (skillDocument.isPresent) Optional.of(skillDocument.get().toSkill()) else Optional.empty()
    }

    override fun findAll(): List<Skill> {
        return skillMongoRepository.findAll()
            .map { it.toSkill() }
    }

    override fun deleteAll() {
        skillMongoRepository.deleteAll()
    }
}

@Component
interface SkillMongoRepository : MongoRepository<SkillDocument, UUID> {
    fun findByName(name: String): Optional<SkillDocument>
}
