package kg.bakai.repo.domain.repository

import kg.bakai.repo.domain.model.Repository
import kg.bakai.repo.utils.Resource

interface DataRepository {
    suspend fun fetchData(user: String): Resource<List<Repository>>
}