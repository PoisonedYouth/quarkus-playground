package com.poisonedyouth.quarkus.domain.infrastructure.adapter.input

import com.poisonedyouth.quarkus.domain.entity.User
import com.poisonedyouth.quarkus.domain.vo.Age
import com.poisonedyouth.quarkus.domain.vo.Name
import com.poisonedyouth.quarkus.domain.vo.toIdentity

data class UserDto(
        val id: Long? = null,
        val name: String,
        val age: Int
)

fun UserDto.toUser() = User(
        identity = this.id.toIdentity(),
        name = Name(this.name.split(" ").first(), this.name.split(" ").last()),
        age = Age(this.age)
)

fun User.toUserDto() = UserDto(
        id = this.identity.id,
        name = this.name.toString(),
        age = this.age.value
)