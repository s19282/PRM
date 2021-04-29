package pl.edu.pja.pysznepja.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.pysznepja.databinding.ItemImageBinding

class DishImage(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bindImage(drawable: Drawable)
    {
        binding.image.setImageDrawable(drawable)
    }
    fun setSelection(state: Boolean)
    {
        binding.frame.visibility = if (state) View.VISIBLE else View.INVISIBLE
    }
}

class ImageAdapter(private val images: List<Drawable>): RecyclerView.Adapter<DishImage>()
{
    var selectedItem: Int? = null
    val selectedDrawable: Drawable?
        get() = selectedItem?.let { images[it]}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishImage {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DishImage(binding).also { holder -> binding.root.setOnClickListener{
            setSelection(holder.layoutPosition)
        } }
    }

    private fun setSelection(layoutPosition: Int) {
        val oldPosition = selectedItem
        selectedItem = layoutPosition
        oldPosition?.let { notifyItemChanged(it) }
        notifyItemChanged(layoutPosition)
    }

    override fun onBindViewHolder(holder: DishImage, position: Int) {
        holder.apply {
            bindImage(images[position])
            setSelection(position == selectedItem)
        }
    }

    override fun getItemCount(): Int = images.size

}