package com.works.hackathon.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.works.hackathon.R
import com.works.hackathon.model.ExpenseProduct


class ExpenseProductsAdapter(
    private val context: Context,
    private var items: List<ExpenseProduct>,

) : RecyclerView.Adapter<ExpenseProductsAdapter.ExpenseProductsViewHolder>() {


    inner class ExpenseProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expenseProductImage: ImageView = itemView.findViewById(R.id.expenseProductImage)
        val expenseProductButton: Button = itemView.findViewById(R.id.expenseProductAddBtn)
        val expenseProductPrice: TextView = itemView.findViewById(R.id.txtExpenseProductPrice)
        val expenseProductTitle: TextView = itemView.findViewById(R.id.expenseProductTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ExpenseProductsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_expense_product, parent, false)
        return ExpenseProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseProductsAdapter.ExpenseProductsViewHolder, position: Int) {
        Glide.with(context).load(items[position].image).into(holder.expenseProductImage)
        holder.expenseProductPrice.text = items[position].price.toString()
        holder.expenseProductTitle.text = items[position].title


        /*
        holder.cardView1.setOnClickListener {
            val item = items[position]
            if (isSelling) {
                Log.e("Selling","alış $isPurchase ve satış $isSelling")
                val action =
                    ActivitiesFragmentDirections.actionActionsFragmentToSellPageFragment(item._id)
                holder.itemView.findNavController().navigate(action)
            } else if (isPurchase) {
                Log.e("Purchase","alış $isPurchase ve satış $isSelling")

                val action =
                    ActivitiesFragmentDirections.actionActionsFragmentToProductBuyDetailFragment2(
                        item._id
                    )
                holder.itemView.findNavController().navigate(action)
            }
        }

         */

    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newProductList: List<ExpenseProduct>) {
        items = newProductList
        notifyDataSetChanged()
    }


}


