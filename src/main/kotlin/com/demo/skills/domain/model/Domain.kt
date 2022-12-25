package com.demo.skills.domain.model

enum class Domain(val value: String) {
    TECH("Tech"), BUSINESS("Business"), LEADERSHIP("Leadership");

    companion object {
        fun from(value: String): Domain {
            return Domain.values().find { it.value == value }
                ?: throw IllegalArgumentException("Domain for $value doesn't exsits")
        }
    }
}
