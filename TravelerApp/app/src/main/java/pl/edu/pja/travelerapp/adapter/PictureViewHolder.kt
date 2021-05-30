package pl.edu.pja.travelerapp.adapter

import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.travelerapp.databinding.PictureItemBinding
import pl.edu.pja.travelerapp.model.Picture_

class PictureViewHolder(private val binding: PictureItemBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bindPicture(picture: Picture_)
    {
        binding.apply{
            imageView3.setImageBitmap(picture.image)
        }
    }
}