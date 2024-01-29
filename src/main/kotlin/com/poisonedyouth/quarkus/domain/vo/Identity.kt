package com.poisonedyouth.quarkus.domain.vo

sealed interface Identity<out T> {
    val id: T
}

data object NoIdentity : Identity<Nothing> {
    override val id: Nothing
        get() = error("Identity not initialized yet.")
}

data class LongIdentity(override val id: Long) : Identity<Long>

fun Long?.toIdentity(): Identity<Long> {
    return if (this == null) {
        NoIdentity
    } else {
        LongIdentity(this)
    }
}