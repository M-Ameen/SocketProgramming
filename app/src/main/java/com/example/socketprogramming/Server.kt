package com.example.socketprogramming

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket


fun main() {
    var serverSocket: ServerSocket? = null
    var clientSocket: Socket? = null
    var inputStreamReader: InputStreamReader? = null
    var bufferedReader: BufferedReader? = null
    var message = ""

    try {
        serverSocket = ServerSocket(4444)
    } catch (e: IOException) {
        println("Could not listen on port: 4444")
        return
    }

    println("Server started. Listening to the port 4444")

    while (message != "over") {
        try {
            clientSocket = serverSocket.accept()
            inputStreamReader = InputStreamReader(clientSocket.getInputStream())
            bufferedReader = BufferedReader(inputStreamReader)
            message = bufferedReader.readLine()
            println(message)

            inputStreamReader.close()
            clientSocket.close()
        } catch (ex: IOException) {
            println("Problem in message reading")
        }
    }
}

