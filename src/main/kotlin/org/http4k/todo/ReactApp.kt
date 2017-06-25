package org.http4k.todo

import org.http4k.core.ContentType
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.CorsPolicy.Companion.UnsafeGlobalPermissive
import org.http4k.filter.ServerFilters.Cors
import org.http4k.routing.ResourceLoader.Companion.Directory
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main(args: Array<String>) {
    val port = if (args.isNotEmpty()) args[0] else "5000"

    Cors(UnsafeGlobalPermissive)
        .then(routes(
            "/api" bind routes(
                "/" to GET bind { Response(OK) }
            ),
            "/" bind static(Directory("src/main/resources"), "jsx" to ContentType("text/jsx"))
        ))
        .asServer(Jetty(port.toInt())).start().block()
}
