package pl.edu.pja.pysznepja.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.pysznepja.databinding.ItemDishBinding
import pl.edu.pja.pysznepja.databinding.ItemImageBinding
import pl.edu.pja.pysznepja.model.Dish

class DishVh(private val binding: ItemDishBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(dish: Dish)
    {
        with(binding)
        {
            name.text = dish.name
            ingredients.text = dish.ingredients.joinToString()
            image.setImageDrawable(dish.photo)
        }
    }
}

class DishAdapter(initList: List<Dish>): RecyclerView.Adapter<DishVh>()
{
    var list: List<Dish> = initList
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishVh {
        val binding = ItemDishBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DishVh(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DishVh, position: Int) {
        holder.bind(list[position])
    }

}