package uk.co.logiccache.petstore.service

import uk.co.logiccache.petstore.model.Pet
import java.util.concurrent.atomic.AtomicReference

interface PetstoreService {
    fun petById(id:Int): Pet
    fun allPets(): List<Pet>
    fun createPet(pet: Pet)
}

class PetstoreServiceImpl(initialPets: MutableList<Pet>) : PetstoreService {
     private val pets = initialPets

    override fun petById(id: Int): Pet {
        return pets.first { it.id == id }
    }

    override fun allPets(): List<Pet> {
        return pets
    }

    override fun createPet(pet: Pet) {
        pets.add(pet)
    }
}