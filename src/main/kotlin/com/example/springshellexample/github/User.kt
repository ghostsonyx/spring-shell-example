package com.example.springshellexample.github

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class User (
    val login: String?,

    val id: String?,

    val url: String?,

    @JsonProperty("repos_url")
    val reposUrl: String?,

    val name: String?,

    val email: String?,

    val followers: Long,

    val following: Long,

    @JsonProperty("created_at")
    val created: LocalDate?,

    @JsonProperty("updated_at")
    val updated: LocalDate?,
)
