package com.poisonedyouth.quarkus.domain.entity

import com.poisonedyouth.quarkus.domain.vo.Age
import com.poisonedyouth.quarkus.domain.vo.Identity
import com.poisonedyouth.quarkus.domain.vo.Name

data class User(
    val identity: Identity<Long>,
    val name: Name,
    val age: Age
)
