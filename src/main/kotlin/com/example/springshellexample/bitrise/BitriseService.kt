package com.example.springshellexample.bitrise

import com.example.springshellexample.webClient
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
@Slf4j
class BitriseService {

    @Value("\${bitrise.auth.token}")
    lateinit var bitriseAuthToken: String

    fun getBitriseUserDetails(): User? {
        var data = webClient()
                .get()
                .uri("https://api.bitrise.io/v0.1/me")
                .header(HttpHeaders.AUTHORIZATION, bitriseAuthToken)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
                .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
                .toEntity(BitriseUserData::class.java)
                .block()

        return data?.body?.data?.toUserReflection()
    }
}
