package uk.co.logiccache.petstore.web

import org.http4k.core.*
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.format.Jackson.auto
import org.http4k.lens.Path
import org.http4k.lens.Query
import org.http4k.lens.int
import org.http4k.lens.string
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uk.co.logiccache.petstore.model.Error
import uk.co.logiccache.petstore.model.Pet
import uk.co.logiccache.petstore.service.PetstoreService

object PetstoreResource : KoinComponent {

    private val petstoreService by inject<PetstoreService>()

    operator fun invoke(): HttpHandler = PrintRequest()
        .then(routes(listPets(), createPets(), showPetById()))

    private fun listPets(): RoutingHttpHandler {
        val petsLens = Body.auto<List<Pet>>().toLens()
        val errorLens = Body.auto<Error>().toLens()
        val limitLens = Query.int().optional("limit")

        return "/pets" bind Method.GET to { request: Request ->
            val limit = limitLens(request)
            Response(Status.OK).with(petsLens of petstoreService.allPets())
        }
    }

    private fun showPetById(): RoutingHttpHandler {
        val petLens = Body.auto<Pet>().toLens()
        val errorLens = Body.auto<Error>().toLens()
        val petIdLens = Path.string().of("petId")

        return "/pets/{petId}" bind Method.GET to { request: Request ->
            val petId = petIdLens(request).toInt()
            Response(Status.OK).with(petLens of petstoreService.petById(petId))
        }
    }

    private fun createPets(): RoutingHttpHandler {
        val errorLens = Body.auto<Error>().toLens()
        val petLens = Body.auto<Pet>().toLens()

        return "/pets" bind Method.POST to { request: Request ->
            petstoreService.createPet(petLens(request))
            Response(Status.CREATED)
        }
    }
}


