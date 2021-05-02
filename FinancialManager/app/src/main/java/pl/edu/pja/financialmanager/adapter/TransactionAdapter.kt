package pl.edu.pja.financialmanager.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.financialmanager.AddActivity
import pl.edu.pja.financialmanager.Shared
import pl.edu.pja.financialmanager.databinding.ItemTransferBinding
import pl.edu.pja.financialmanager.model.Transaction

class TransactionAdapter(initList: List<Transaction>) : RecyclerView.Adapter<TransactionViewHolder>()
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
            binding.root.setOnLongClickListener{
            removeItem(holder.layoutPosition,parent)
        }
            binding.root.setOnClickListener{
                parent.context.startActivity(Intent(parent.context, AddActivity::class.java).putExtra("id",holder.layoutPosition))
            }
        }
//        TODO: onClick 1:05:22 04.19
    }


    private fun removeItem(position: Int, parent: ViewGroup): Boolean
    {
//        TODO: check once more
        val builder = AlertDialog.Builder(parent.context)
        builder.setMessage("Are you sure you want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                Shared.transactionList.removeAt(position)
                notifyItemChanged(position)
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