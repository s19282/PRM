package pl.edu.pja.travelerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.travelerapp.MainActivity
import pl.edu.pja.travelerapp.databinding.PictureItemBinding

class PictureAdapter(private val mainActivity: MainActivity) : RecyclerView.Adapter<TransactionViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = PictureItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PictureViewHolder(binding).also { holder ->
            //binding.root.setOnLongClickListener{ removeItem(holder.layoutPosition,parent) }
            binding.root.setOnClickListener{ editItem(holder.layoutPosition) }
        }
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
                Shared.transactionList.removeAt(position)
                mainActivity.updateSum()
                notifyItemRemoved(position)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
        return true
    }


    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bindTransaction(list[position])
    }

    override fun getItemCount(): Int = list.size

}