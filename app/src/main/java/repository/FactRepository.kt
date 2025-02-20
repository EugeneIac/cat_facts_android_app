package repository

import android.util.Log
import model.Fact
import network.RetrofitClient
import retrofit2.Response

class FactRepository {

    companion object {
        private const val TAG = "FactRepository"
    }

    suspend fun fetchFact(): Response<Fact> {
        Log.d(TAG, "Выполняется запрос к API (fetchFact)")
        return RetrofitClient.apiService.getFact(language = "rus", count = 1)
    }
}
