package uk.co.logiccache.petstore

import org.koin.dsl.module
import uk.co.logiccache.petstore.model.Pet
import uk.co.logiccache.petstore.service.PetstoreService
import uk.co.logiccache.petstore.service.PetstoreServiceImpl

val petstoreModule = module {
    single<PetstoreService> { PetstoreServiceImpl(mutableListOf()) }
}