package com.example.springshellexample.github

import com.example.springshellexample.webClient
import lombok.extern.slf4j.Slf4j
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.stream.Collectors

@Service
@Slf4j
class GithubService {
    fun getGithubUserDetails(userName: String): User? {
        var user = webClient()
                .get()
                .uri("https://api.github.com/users/$userName")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
                .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
                .toEntity(User::class.java)
                .block()

        return user?.body
    }

    fun getGithubUserRepos(userName: String): MutableList<String?>? {
        var fluxRepos = WebClient.create()
                .get()
                .uri("https://api.github.com/users/$userName/repos")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
                .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
                .toEntity(object : ParameterizedTypeReference<List<Repo?>?>() {})
                .block()

        return fluxRepos?.body?.stream()?.map { repo: Repo? -> repo?.name }?.collect(Collectors.toList())
    }
}
