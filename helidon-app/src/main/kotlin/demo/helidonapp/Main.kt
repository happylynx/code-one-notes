package demo.helidonapp

import io.helidon.webserver.Handler
import io.helidon.webserver.Routing
import io.helidon.webserver.WebServer
import java.util.concurrent.TimeUnit

/*
TODO
* simple main to compare package size
* configurable port
* serving page+image from resources
* cdi demo in fallback image
    * observe process tree of fallback app
    * does fallback app follow JAVA_HOME?
* regular native image
 */

/*
..\..\..\opt\graalvm\graalvm-ce-19.2.1\bin\java -cp ... demo.helidonapp.Main
..\..\..\opt\graalvm\graalvm-ce-19.2.1\bin\native-image -cp ... demo.helidonapp.Main helidonapp
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