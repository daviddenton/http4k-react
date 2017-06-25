package org.http4k.react

import org.http4k.core.then
import org.http4k.filter.CorsPolicy.Companion.UnsafeGlobalPermissive
import org.http4k.filter.ResponseFilters
import org.http4k.filter.ServerFilters.Cors
import org.http4k.routing.ResourceLoader
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Http4kServer
import org.http4k.server.Jetty
import org.http4k.server.asServer
import java.time.Clock
import java.time.LocalDateTime

object ReactHttp4kApp {

    operator fun invoke(port: Int, resourceLoader: ResourceLoader): Http4kServer {
        val clock = Clock.systemUTC()
        val auditor = ResponseFilters.ReportLatency { req, resp, duration -> println("${LocalDateTime.now(clock)} - ${req.method} - ${req.uri} - ${resp.status.code} - ${duration.toMillis()}ms") }

        return Cors(UnsafeGlobalPermissive)
            .then(routes(
                "/api" bind auditor.then(Api()),
                "/" bind static(resourceLoader)
            ))
            .asServer(Jetty(port))
    }
}
