package org.http4k.react

import org.http4k.core.then
import org.http4k.filter.CorsPolicy.Companion.UnsafeGlobalPermissive
import org.http4k.filter.ServerFilters.Cors
import org.http4k.routing.ResourceLoader.Companion.Classpath
import org.http4k.routing.ResourceLoader.Companion.Directory
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main(args: Array<String>) {
    val port = if (args.isNotEmpty()) args[0] else "5000"

    val dev = true
    val resourceLoader = if(dev) Directory("src/main/resources") else Classpath()
    Cors(UnsafeGlobalPermissive)
        .then(routes(
            Api(),
            static(resourceLoader)
        ))
        .asServer(Jetty(port.toInt())).start().block()
}
