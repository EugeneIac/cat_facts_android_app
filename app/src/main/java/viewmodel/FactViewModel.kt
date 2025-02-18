package viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import repository.FactRepository

class FactViewModel : ViewModel() {

    private val repository = FactRepository()

    companion object {
        private const val TAG = "FactViewModel"
    }

    // MutableLiveData для хранения текста факта
    private val _factLiveData = MutableLiveData<String>()
    val factLiveData: LiveData<String>
        get() = _factLiveData

    // Загружаем факт при создании ViewModel
    init {
        refreshFact()
    }

    // Функция обновления факта, вызываемая по нажатию кнопки
    fun refreshFact() {
        viewModelScope.launch {
            Log.d(TAG, "Запуск обновления факта")
            try {
                val response = repository.fetchFact()
                if (response.isSuccessful) {
                    val fact = response.body()
                    if (fact != null) {
                        _factLiveData.value = fact.text
                        Log.d(TAG, "Новый факт: ${fact.text}")
                    } else {
                        _factLiveData.value = "Пустой ответ"
                        Log.w(TAG, "Получен пустой факт")
                    }
                } else {
                    _factLiveData.value = "Ошибка: ${response.code()}"
                    Log.e(TAG, "Ошибка: код ответа = ${response.code()}")
                }
            } catch (e: Exception) {
                _factLiveData.value = "Исключение: ${e.message}"
                Log.e(TAG, "Исключение при обновлении факта: ${e.message}", e)
            }
        }
    }
}
