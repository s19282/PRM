package pl.edu.pja.financialmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        return TransactionViewHolder(binding)
//        TODO: onClick 1:05:22 04.19
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bindTransaction(list[position])
    }

    override fun getItemCount(): Int = list.size

}