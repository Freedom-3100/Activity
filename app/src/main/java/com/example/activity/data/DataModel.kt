package com.example.activity.data

import com.example.activity.Requester
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepository(
    private val characterDao: CharacterDao,
    private val requester: Requester
) {
    fun getAllCharacters(): Flow<List<CharacterEntity>> {
        return characterDao.getAllCharacters().map { entities ->
            entities.map { entity ->
                CharacterEntity(
                    id = entity.id,
                    name = entity.name,
                    status = entity.status,
                    species = entity.species,
                    image = entity.image
                )
            }
        }
    }

    suspend fun refreshData() {
        try {
            val response = requester.getData()
            val entities = response.results.map { apiCharacter ->
                CharacterEntity(
                    id = apiCharacter.id,
                    name = apiCharacter.name,
                    status = apiCharacter.status,
                    species = apiCharacter.species,
                    image = apiCharacter.image
                )
            }
            characterDao.deleteAll()
            characterDao.insertAll(entities)
            println("Данные сохранены в БД: ${entities.size} персонажей")
        } catch (e: Exception) {
            println("Ошибка при обновлении данных: ${e.message}")
            throw e
        }
    }
}