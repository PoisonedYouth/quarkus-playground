package com.poisonedyouth.quarkus.domain.infrastructure.adapter.output

import com.poisonedyouth.quarkus.domain.application.ports.output.UserOutputPort
import com.poisonedyouth.quarkus.domain.entity.User
import com.poisonedyouth.quarkus.domain.vo.Identity
import com.poisonedyouth.quarkus.domain.vo.NoIdentity
import com.poisonedyouth.quarkus.domain.vo.toIdentity
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional

@Transactional
@ApplicationScoped
class JpaUserOutputPort(
        private val entityManager: EntityManager
) : UserOutputPort {

    override fun save(user: User): Identity<Long> {
        return if (user.identity is NoIdentity) {
            val entity = UserPersistenceEntity(
                    firstName = user.name.firstName,
                    lastName = user.name.lastName,
                    age = user.age.value
            )
            entityManager.persist(
                    entity
            )
            entity.id.toIdentity()
        } else {
            user.identity
        }
    }

    override fun findBy(identity: Identity<Long>): User? {
        return entityManager.find(UserPersistenceEntity::class.java, identity.id)?.toUser()
    }
}