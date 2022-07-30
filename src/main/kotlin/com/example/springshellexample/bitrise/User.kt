package com.example.springshellexample.bitrise

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.reflect.full.memberProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BitriseUserData (
    val data: Data?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Data (
    @JsonProperty("username")
    val userName: String?,

    val slug: String?,

    val email : String?,

    @JsonProperty("avatar_url")
    val avatarUrl : String?,

    @JsonProperty("created_at")
    val created : String?,

    @JsonProperty("data_id")
    val id : Int?
)

data class User (
    val userName: String?,
    val slug: String?,
    val email : String?,
    val avatarUrl : String?,
    val created : String?,
    val id : Int?
)

fun Data.toUserReflection() = with(::User) {
    val propertiesByName = Data::class.memberProperties.associateBy { it.name }

    callBy(args = parameters.associate { parameter ->
        parameter to when (parameter.name) {
            "slug" -> "$slug"
            else -> propertiesByName[parameter.name]?.get(this@toUserReflection)
        }
    })
}
