package com.poisonedyouth.quarkus.domain.vo

@JvmInline
value class Age(val value: Int) {
    init {
        require(value in 0..100) {
            "Age must be between 0 and 100."
        }
    }
}