package org.http4k.react;

import org.http4k.routing.ResourceLoader

fun main(args: Array<String>) = ReactHttp4kApp(args[0].toInt(), ResourceLoader.Classpath()).start().block()
