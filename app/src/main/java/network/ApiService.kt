package network

import model.Fact
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("facts/random")
    suspend fun getFact(
        @Query("animal_type") animalType: String = "cat",
        @Query("amount") amount: Int = 1
    ): Response<Fact>
}
