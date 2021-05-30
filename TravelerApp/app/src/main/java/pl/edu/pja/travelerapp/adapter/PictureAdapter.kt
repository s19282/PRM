package pl.edu.pja.travelerapp.adapter

import android.app.AlertDialog
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.travelerapp.MainActivity
import pl.edu.pja.travelerapp.Shared
import pl.edu.pja.travelerapp.databinding.PictureItemBinding
import pl.edu.pja.travelerapp.model.Picture_
import kotlin.concurrent.thread

class PictureAdapter(private val mainActivity: MainActivity) : RecyclerView.Adapter<PictureViewHolder>()
{
    private val handler = HandlerCompat.createAsync(Looper.getMainLooper())
    var pictures: MutableList<Picture_> = mutableListOf()
        set(value) {
            field = value
            handler.post {
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = PictureItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PictureViewHolder(binding)
//            .also { holder ->
//            binding.root.setOnLongClickListener{ removeItem(holder.layoutPosition,parent) }
//            binding.root.setOnClickListener{ editItem(holder.layoutPosition) }
//        }
    }

    private fun editItem(id: Int)
    {
        mainActivity.openShowPictureActivity(id)
        notifyItemChanged(id)
    }


    private fun removeItem(position: Int, parent: ViewGroup): Boolean
    {
        val builder = AlertDialog.Builder(parent.context)
        builder.setMessage("Are you sure you want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                thread {
                    Shared.db?.note?.delete(position.toLong())
                    pictures.removeAt(position);
                    mainActivity.runOnUiThread{
                        notifyItemRemoved(position)
                    }
                }
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
        return true
    }


    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bindPicture(pictures[position])
    }

    override fun getItemCount(): Int = pictures.size

}