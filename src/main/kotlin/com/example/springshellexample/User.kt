package com.example.springshellexample

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class User (
    var login: String?,
    var id: String?,
    var url: String?,
    var repos_url: String?,
    var name: String?,
    var email: String?,
    var followers: Long,
    var following: Long,
    var created_at: LocalDate?,
    var updated_at: LocalDate?,
)
