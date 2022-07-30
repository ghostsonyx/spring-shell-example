package com.example.springshellexample.github

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repo (
    val name: String? = null
)
