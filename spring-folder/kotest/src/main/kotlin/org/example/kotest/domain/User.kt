package org.example.kotest.domain

import jakarta.persistence.Entity

@Entity
class User(
    val id: Long? = null,
    val email: String,
    val password: String
)
