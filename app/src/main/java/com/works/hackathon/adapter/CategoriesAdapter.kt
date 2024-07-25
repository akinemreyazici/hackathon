package com.works.hackathon.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.works.hackathon.R
import com.works.hackathon.model.Category
import com.works.hackathon.model.Expense
import com.works.hackathon.view.HomepageFragmentDirections
import org.w3c.dom.Text


class CategoriesAdapter(
    private val context: Context,
    private var items: List<Category>,

) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTitle: TextView = itemView.findViewById(R.id.categoryTitle)
        val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)
        val categoryCardView: CardView = itemView.findViewById(R.id.categoryCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_category, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        Glide.with(context).load(items[position].image).into(holder.categoryImage)
        holder.categoryTitle.text = items[position].category

        holder.categoryCardView.setOnClickListener {
            val item = items[position]
            val action =
                HomepageFragmentDirections.actionHomepageFragmentToProductsFragment(item.category)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCategoryList: List<Category>) {
        items = newCategoryList
        notifyDataSetChanged()
    }
}