package pl.edu.pja.financialmanager.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.financialmanager.activity.AddActivity
import pl.edu.pja.financialmanager.activity.MainActivity
import pl.edu.pja.financialmanager.db.Shared
import pl.edu.pja.financialmanager.databinding.ItemTransferBinding
import pl.edu.pja.financialmanager.model.Transaction

class TransactionAdapter(initList: List<Transaction>, private val mainActivity: MainActivity) : RecyclerView.Adapter<TransactionViewHolder>()
{
    var list: List<Transaction> = initList
        set(value)
        {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return TransactionViewHolder(binding).also { holder ->
            binding.root.setOnLongClickListener{ removeItem(holder.layoutPosition,parent) }
            binding.root.setOnClickListener{ editItem(holder.layoutPosition) }
        }
    }

    private fun editItem(id: Int)
    {
        mainActivity.openEditActivity(id)
        notifyItemChanged(id)
    }


    private fun removeItem(position: Int, parent: ViewGroup): Boolean
    {
        val builder = AlertDialog.Builder(parent.context)
        builder.setMessage("Are you sure you want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                Shared.transactionList.removeAt(position)
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