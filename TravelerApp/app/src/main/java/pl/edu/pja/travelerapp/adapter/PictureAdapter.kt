package pl.edu.pja.travelerapp.adapter

import android.app.AlertDialog
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.travelerapp.activity.MainActivity
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
            .also { holder ->
            binding.root.setOnLongClickListener{ removeItem(holder.layoutPosition,parent) }
            binding.root.setOnClickListener{ mainActivity.openShowPictureActivity(pictures[holder.layoutPosition].id) }
        }
    }


    private fun removeItem(position: Int, parent: ViewGroup): Boolean
    {
        val builder = AlertDialog.Builder(parent.context)
        builder.setMessage("Are you sure you want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                thread {
                    mainActivity.deletePhoto(pictures[position].imageName)
                    mainActivity.geofencingClient.removeGeofences(mutableListOf(pictures[position].id.toString()))
                    Shared.db?.note?.delete(pictures[position].id)
                    pictures.removeAt(position)
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