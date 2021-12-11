package com.ayushman999.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayushman999.shoppinglist.R
import com.ayushman999.shoppinglist.data.db.ShoppingDatabase
import com.ayushman999.shoppinglist.data.db.entities.ShoppingItem
import com.ayushman999.shoppinglist.data.other.ShoppingItemAdapter
import com.ayushman999.shoppinglist.data.repository.ShoppingRepository
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)


        val viewModel= ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adapter=ShoppingItemAdapter(listOf(),viewModel)
        rvShippingItems.layoutManager=LinearLayoutManager(this)
        rvShippingItems.adapter=adapter
        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items=it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,object :AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }

            }).show()
        }
    }
}