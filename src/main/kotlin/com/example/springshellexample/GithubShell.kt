package com.example.springshellexample

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
open class GithubShell {
    @Autowired
    private val githubService: GithubService? = null
    @ShellMethod("getUser")
    fun getUser(userName: String?): User? {
        return githubService!!.getUser(userName!!)
    }

    @ShellMethod("getRepos")
    fun getRepos(userName: String?): MutableList<String?>? {
        return githubService!!.getRepos(userName!!)
    }
}
