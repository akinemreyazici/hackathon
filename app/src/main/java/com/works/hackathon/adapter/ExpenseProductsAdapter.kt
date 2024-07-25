package com.works.hackathon.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.works.hackathon.R
import com.works.hackathon.config.AppDatabase
import com.works.hackathon.model.ExpenseProduct
import com.works.hackathon.room.ExpenseProductRoomRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ExpenseProductsAdapter(
    private val context: Context,
    private val application: Application,
    private var items: List<ExpenseProduct>,
) : RecyclerView.Adapter<ExpenseProductsAdapter.ExpenseProductsViewHolder>() {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    private val productDao = db.expenseProductDao()
    private val productRoomRepositories = ExpenseProductRoomRepositories(productDao)

    inner class ExpenseProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expenseProductImage: ImageView = itemView.findViewById(R.id.expenseProductImage)
        val expenseProductButton: Button = itemView.findViewById(R.id.expenseProductAddBtn)
        val expenseProductPrice: TextView = itemView.findViewById(R.id.txtExpenseProductPrice)
        val expenseProductTitle: TextView = itemView.findViewById(R.id.expenseProductTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_expense_product, parent, false)
        return ExpenseProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseProductsViewHolder, position: Int) {
        val product = items[position]
        Glide.with(context).load(product.image).into(holder.expenseProductImage)
        holder.expenseProductPrice.text = product.price.toString()
        holder.expenseProductTitle.text = product.title

        holder.expenseProductButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                productRoomRepositories.addOrUpdateProduct(product)
            }
        }
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



