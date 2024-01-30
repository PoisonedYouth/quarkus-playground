package com.poisonedyouth.quarkus.domain.vo

data class Name(
    val firstName: String,
    val lastName: String
){
    init {
        require(firstName.isNotEmpty()){
            "Firstname must not be empty."
        }
        require(lastName.isNotEmpty()){
            "Lastname must not be empty."
        }
    }

    override fun toString(): String {
        return "$firstName $lastName"
    }
}
