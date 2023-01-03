package com.demo.skills.domain.model

enum class Domain(val label: String) {
    TECH("Tech"), BUSINESS("Business"), LEADERSHIP("Leadership");

    companion object {
        fun from(value: String): Domain {
            return Domain.values().find { it.label == value }
                ?: throw IllegalArgumentException("Domain for $value doesn't exsits")
        }
    }
}
