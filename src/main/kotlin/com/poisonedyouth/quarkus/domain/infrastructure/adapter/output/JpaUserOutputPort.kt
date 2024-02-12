package com.poisonedyouth.quarkus.domain.infrastructure.adapter.output

import com.poisonedyouth.quarkus.domain.application.ports.output.UserOutputPort
import com.poisonedyouth.quarkus.domain.entity.User
import com.poisonedyouth.quarkus.domain.vo.Identity
import com.poisonedyouth.quarkus.domain.vo.NoIdentity
import com.poisonedyouth.quarkus.domain.vo.toIdentity
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
@ApplicationScoped
class JpaUserOutputPort(
    private val entityManager: EntityManager
) : UserOutputPort {
    private val logger: Logger = LoggerFactory.getLogger(JpaUserOutputPort::class.java)

    override fun save(user: User): Identity<Long> {
        val entity = UserJpaEntity(
            firstName = user.name.firstName,
            lastName = user.name.lastName,
            age = user.age.value
        )
        return if (user.identity is NoIdentity) {
            logger.info("Persisting new user '$entity'.")
            entityManager.persist(
                entity
            )
            entity.id.toIdentity()
        } else {
            logger.info("Updating existing user '$entity'.")
            entityManager.persist(
                entity.copy(
                    id = user.identity.id
                )
            )
            user.identity
        }
    }

    override fun findBy(identity: Identity<Long>): User? {
        return entityManager.find(UserJpaEntity::class.java, identity.id)?.toUser()
    }
}