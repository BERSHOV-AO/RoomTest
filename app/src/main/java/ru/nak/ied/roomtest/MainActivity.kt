package ru.nak.ied.roomtest

import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.nak.ied.roomtest.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDb.getDb(this)

        binding.button.setOnClickListener{
            val item = Item(null,
                binding.edName.text.toString(),
                binding.edPrice.text.toString()
            )
            val executor = Executors.newSingleThreadExecutor()
            executor.execute{
                db.getDao().insertItem(item)
            }
            executor.shutdown()
        }
    }
}