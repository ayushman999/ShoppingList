package com.ayushman999.shoppinglist.data.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.ayushman999.shoppinglist.R
import com.ayushman999.shoppinglist.data.db.entities.ShoppingItem
import com.ayushman999.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewmodel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem=items[position]
        holder.itemView.tvName.text=curShoppingItem.name
        holder.itemView.tvAmount.text="${curShoppingItem.amount}"

        holder.itemView.ivDelete.setOnClickListener{
            viewmodel.delete(curShoppingItem)
        }
        holder.itemView.ivPlus.setOnClickListener{
            curShoppingItem.amount++
            viewmodel.upsert(curShoppingItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if(curShoppingItem.amount>0){
                curShoppingItem.amount--
                viewmodel.upsert(curShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemview: View):RecyclerView.ViewHolder(itemview)

}