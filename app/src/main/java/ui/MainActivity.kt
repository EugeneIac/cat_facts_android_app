package ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.neveray.catsrandomfacts.R
import com.neveray.catsrandomfacts.databinding.ActivityMainBinding
import viewmodel.FactViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: FactViewModel by viewModels()

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate вызван")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Дополнительно: можно подписаться на LiveData вручную для отладки
        viewModel.factLiveData.observe(this) { text ->
            Log.d(TAG, "Наблюдатель получил: $text")
        }
    }
}
