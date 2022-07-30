package com.example.springshellexample.github

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
open class GithubShell {

    @Autowired
    private val githubService: GithubService? = null
    @ShellMethod("Github User Details")
    fun getGithubUserDetails(userName: String?): User? {
        return githubService!!.getGithubUserDetails(userName!!)
    }

    @ShellMethod("Github User's Repos")
    fun getGithubUserRepos(userName: String?): MutableList<String?>? {
        return githubService!!.getGithubUserRepos(userName!!)
    }
}
