package com.poisonedyouth.quarkus.domain.infrastructure.configuration

import com.poisonedyouth.quarkus.domain.application.ports.input.UserInputPort
import com.poisonedyouth.quarkus.domain.application.ports.output.UserOutputPort
import com.poisonedyouth.quarkus.domain.application.usecases.UserUsecase
import jakarta.enterprise.inject.Produces


class UserConfiguration {

    @Produces
    fun userUsecase(userOutputPort: UserOutputPort): UserUsecase {
        return UserInputPort(userOutputPort)
    }
}