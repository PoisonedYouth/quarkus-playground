package com.poisonedyouth.quarkus.domain.infrastructure.adapter.output

import com.poisonedyouth.quarkus.domain.entity.User
import com.poisonedyouth.quarkus.domain.vo.Age
import com.poisonedyouth.quarkus.domain.vo.LongIdentity
import com.poisonedyouth.quarkus.domain.vo.Name
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "app_user")
data class UserJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val age: Int
)

fun UserJpaEntity.toUser() = User(
    identity = LongIdentity(this.id ?: error("Missing id.")),
    name = Name(this.firstName, this.lastName),
    age = Age(this.age)
)
