package pl.edu.pja.pysznepja

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.pysznepja.adapter.ImageAdapter
import pl.edu.pja.pysznepja.databinding.ActivityAddBinding
import pl.edu.pja.pysznepja.model.Dish
import pl.edu.pja.pysznepja.model.DishDTO
import kotlin.concurrent.thread

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater)}
    private val imageAdapter by lazy { ImageAdapter(loadDrawables()) }
    val drawableIds = listOf(
        R.drawable.pierogi,
        R.drawable.pizza,
        R.drawable.pumpkin,
        R.drawable.spaghetti,
        R.drawable.rosol,
        R.drawable.rice
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
        setupSave()
    }

    private fun setupSave() {
        binding.saveButton.setOnClickListener {
            val selected = imageAdapter.selectedItem ?: return@setOnClickListener
            val dish = DishDTO(
                    name = binding.name.text.toString(),
                    ingredients = binding.ingredients.text.toString(),
                    photoName = resources.getResourceEntryName(drawableIds[selected])
            )
            thread {
                Shared.db?.dish?.insert(dish)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    private fun loadDrawables(): List<Drawable>
    {
        return drawableIds.map { resources.getDrawable(it,theme) }
    }

    private fun setupRecycler() {
        binding.imagesList.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}