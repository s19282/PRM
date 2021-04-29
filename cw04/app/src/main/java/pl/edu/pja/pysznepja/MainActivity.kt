package pl.edu.pja.pysznepja

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.pysznepja.adapter.DishAdapter
import pl.edu.pja.pysznepja.database.AppDatabase
import pl.edu.pja.pysznepja.databinding.ActivityMainBinding
import pl.edu.pja.pysznepja.model.Dish
import kotlin.concurrent.thread

private const val REQUEST_ADD_DISH = 1

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val dishAdapter by lazy {DishAdapter(Shared.dishList)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Shared.db = AppDatabase.open(applicationContext)
        setupDishList()

    //        binding.addButton.setOnClickListener { openAddActivity()}

    }

    private fun setupDishList() {
        binding.dishList.apply {
            adapter = dishAdapter
            layoutManager = LinearLayoutManager(context)
        }
        refreshData()
    }

    private fun refreshData() {
        thread {
            val data = Shared.db?.dish?.selectAll() ?: return@thread
            val mappedData = data.map{
                val id = resources.getIdentifier(
                        it.photoName, "drawable", "pl.edu.pja.pysznepja"

                )
                Dish(it.name, it.ingredients.split("\n"), resources.getDrawable(id,theme) )
            }
            runOnUiThread {dishAdapter.list = mappedData}
        }
    }

    override fun onResume() {
        super.onResume()
        dishAdapter.list = Shared.dishList
    }

    fun openAddActivity(view: View)
    {
        startActivityForResult(
                Intent(this, AddActivity::class.java),
                REQUEST_ADD_DISH
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_ADD_DISH && resultCode == Activity.RESULT_OK)
        {
//            dishAdapter.list = Shared.dishList
//            Refresh
            refreshData()
        }
        else super.onActivityResult(requestCode, resultCode, data)

    }
}