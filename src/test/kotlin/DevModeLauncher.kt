import org.http4k.react.ReactHttp4kApp
import org.http4k.routing.ResourceLoader


fun main(args: Array<String>) {
    Thread {
        ProcessBuilder("npm", "run-script", "watch").inheritIO().start().waitFor()
    }.start()

    ReactHttp4kApp(5000, ResourceLoader.Directory("src/main/resources")).start().block()
}
