package pl.edu.pja.pysznepja

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import pl.edu.pja.pysznepja.adapter.ImageAdapter
import pl.edu.pja.pysznepja.databinding.ActivityAddBinding
import pl.edu.pja.pysznepja.databinding.ActivityMainBinding
import pl.edu.pja.pysznepja.model.Dish

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater)}
    private val imageAdapter by lazy { ImageAdapter(loadDrawables()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
        setupSave()
    }

    private fun setupSave() {
        binding.saveButton.setOnClickListener {
            val selected = imageAdapter.selectedDrawable ?: return@setOnClickListener
            val dish = Dish(
                    binding.name.text.toString(),
                    binding.ingredients.text.toString().split('\n'),
                    selected
            )
            Shared.dishList.add(dish)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun loadDrawables(): List<Drawable>
    {
        val drawableIds = listOf(
            R.drawable.pierogi,
            R.drawable.pizza,
            R.drawable.pumpkin,
            R.drawable.spaghetti,
            R.drawable.rosol,
            R.drawable.rice
        )
        return drawableIds.map { resources.getDrawable(it,theme) }
    }

    private fun setupRecycler() {
        binding.imagesList.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}