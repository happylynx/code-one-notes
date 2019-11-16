package demo.helidonapp

import io.helidon.webserver.Handler
import io.helidon.webserver.Routing
import io.helidon.webserver.WebServer
import java.util.concurrent.TimeUnit

/*
..\..\..\opt\graalvm\graalvm-ce-19.2.1\bin\java -cp ... demo.helidonapp.Main
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello world")
        val webServer = WebServer
            .create(
                Routing.builder()
                    .any(Handler { req, res -> res.send("It works!") })
                    .build()
            )
            .start()
            .toCompletableFuture()
            .get(10, TimeUnit.SECONDS)

        println("Server started at: http://localhost:" + webServer.port())
    }
}