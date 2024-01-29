package com.poisonedyouth.quarkus.domain.application.ports.output

import com.poisonedyouth.quarkus.domain.entity.User
import com.poisonedyouth.quarkus.domain.vo.Identity

interface UserOutputPort {
    fun save(user: User): Identity<Long>

    fun findBy(identity: Identity<Long>): User?
}