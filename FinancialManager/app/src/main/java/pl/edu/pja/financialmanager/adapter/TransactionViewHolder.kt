package pl.edu.pja.financialmanager.adapter

import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.financialmanager.R
import pl.edu.pja.financialmanager.databinding.ItemTransferBinding
import pl.edu.pja.financialmanager.model.Transaction

class TransactionViewHolder(private val binding: ItemTransferBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bindTransaction(transaction: Transaction)
    {
        with(binding)
        {
            category.text = root.resources.getStringArray(R.array.categories)[transaction.category.toInt()]
            amount.text = transaction.amount.toString()
            date.text = transaction.date.toString()
            name.text = transaction.place
            image.setImageDrawable(transaction.image)
        }
    }
}