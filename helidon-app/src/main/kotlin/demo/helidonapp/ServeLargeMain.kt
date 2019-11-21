package demo.helidonapp

import demo.helidonapp.util.InputStreamPublisher
import io.helidon.common.http.MediaType
import io.helidon.webserver.Handler
import io.helidon.webserver.Routing
import io.helidon.webserver.WebServer
import java.util.concurrent.TimeUnit

object ServeLargeMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val webServer = WebServer
            .create(
                Routing.builder()
                    .any(Handler { req, res ->
                        res.headers().contentType(MediaType.APPLICATION_OCTET_STREAM)
                        res.send(
                            InputStreamPublisher(
                                ServeLargeMain::class.java.getResourceAsStream("large.dat")
                            )
                        )
                    })
                    .build()
            )
            .start()
            .toCompletableFuture()
            .get(10, TimeUnit.SECONDS)

        println("Server started at: http://localhost:" + webServer.port())
    }
}