package kg.bakai.repo.data.network

import kg.bakai.repo.domain.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{user}/repos")
    fun fetchRepos(
        @Path("user") user: String
    ): Call<List<Repository>>
}