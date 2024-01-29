package com.poisonedyouth.quarkus.domain.application.ports.input

import com.poisonedyouth.quarkus.domain.application.ports.output.UserOutputPort
import com.poisonedyouth.quarkus.domain.application.usecases.UserUsecase
import com.poisonedyouth.quarkus.domain.entity.User
import com.poisonedyouth.quarkus.domain.vo.Identity
import com.poisonedyouth.quarkus.domain.vo.NoIdentity

class UserInputPort(
    private val userOutputPort: UserOutputPort
) : UserUsecase {
    override fun addNew(user: User): Identity<Long> {
        check(user.identity is NoIdentity) {
            "Id should not be set."
        }
        return userOutputPort.save(user)
    }

    override fun find(identity: Identity<Long>): User? {
        return userOutputPort.findBy(identity)
    }
}