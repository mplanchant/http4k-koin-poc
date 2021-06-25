package uk.co.logiccache.petstore

import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.koin.core.context.GlobalContext.startKoin
import uk.co.logiccache.petstore.web.PetstoreResource

fun main() {
    startKoin {
        printLogger()
        modules(petstoreModule)
    }

    PetstoreResource().asServer(SunHttp(8080)).start()
}
