package com.example.springshellexample.bitrise

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
open class BitriseShell {

    @Autowired
    private val bitriseService: BitriseService? = null

    @ShellMethod("Bitrise User Details")
    fun getBitriseUserDetails(): User? {
        return bitriseService!!.getBitriseUserDetails()
    }

}