package com.example.springshellexample

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.stream.Collectors

@Service
@Slf4j
class GithubService {
    fun getUser(userName: String): User? {
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

    fun getRepos(userName: String): MutableList<String?>? {
        var fluxRepos = WebClient.create()
                .get()
                .uri("https://api.github.com/users/$userName /repos")
                .retrieve()
                .bodyToFlux(Repo::class.java)


        return fluxRepos.collectList().block()
                ?.stream()?.map { repo: Repo -> repo.name }?.collect(Collectors.toList())
    }
}
