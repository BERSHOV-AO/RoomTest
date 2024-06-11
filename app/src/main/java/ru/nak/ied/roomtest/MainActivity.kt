package ru.nak.ied.roomtest

import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import ru.nak.ied.roomtest.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDb.getDb(this)
        db.getDao().getAllItem().asLiveData().observe(this) {list ->
            // каждый раз что бы не добавлялость лишнее будем очищать наш список на экране
            binding.tvlist.text = ""
            list.forEach {
                val text = "Id: ${it.id} Name: ${it.name} Prise: ${it.price}\n"
                binding.tvlist.append(text)
            }
        }

        binding.button.setOnClickListener {
            val item = Item(
                null,
                binding.edName.text.toString(),
                binding.edPrice.text.toString()
            )

//            есть просто вариант с новым потоком
//            Thread {
//                db.getDao().insertItem(item)
//            }.start()

            val executor = Executors.newSingleThreadExecutor()
            executor.execute {
                db.getDao().insertItem(item)
            }
            executor.shutdown()
        }
    }
}