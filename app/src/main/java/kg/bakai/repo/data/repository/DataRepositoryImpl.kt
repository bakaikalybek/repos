package kg.bakai.repo.data.repository

import android.util.Log
import kg.bakai.repo.data.network.ApiService
import kg.bakai.repo.domain.model.Repository
import kg.bakai.repo.domain.repository.DataRepository
import kg.bakai.repo.utils.Resource
import kg.bakai.repo.utils.Result
import kg.bakai.repo.utils.awaitResult

class DataRepositoryImpl(private val apiService: ApiService): DataRepository {
    override suspend fun fetchData(user: String): Resource<List<Repository>> {
        return when (val result = apiService.fetchRepos(user).awaitResult()) {
            is Result.Ok -> {
                if (result.value.isNotEmpty()) {
                    Resource.success(result.value)
                } else {
                    Resource.error(null, "Empty list")
                }
            }
            is Result.Error -> {
                Resource.error(null, result.exception.message)
            }
            else -> {
                Resource.error(null, "Some Error")
            }
        }
    }

}