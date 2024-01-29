package com.poisonedyouth.quarkus.domain.application.usecases

import com.poisonedyouth.quarkus.domain.entity.User
import com.poisonedyouth.quarkus.domain.vo.Identity

interface UserUsecase {
    fun addNew(user: User): Identity<Long>

    fun find(identity: Identity<Long>): User?
}