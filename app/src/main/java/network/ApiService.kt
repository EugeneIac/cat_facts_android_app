package network

import model.Fact
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun getFact(
        @Query("lang") language: String = "rus",
        @Query("count") count: Int = 1
    ): Response<Fact>
}
