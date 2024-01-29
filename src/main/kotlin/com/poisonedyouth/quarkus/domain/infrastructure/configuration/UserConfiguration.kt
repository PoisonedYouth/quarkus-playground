package com.poisonedyouth.quarkus.domain.infrastructure.configuration

import com.poisonedyouth.quarkus.domain.application.ports.input.UserInputPort
import com.poisonedyouth.quarkus.domain.application.ports.output.UserOutputPort
import com.poisonedyouth.quarkus.domain.application.usecases.UserUsecase
import jakarta.enterprise.inject.Produces
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext


class UserConfiguration {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Produces
    fun userUsecase(userOutputPort: UserOutputPort): UserUsecase {
        return UserInputPort(userOutputPort)
    }
}