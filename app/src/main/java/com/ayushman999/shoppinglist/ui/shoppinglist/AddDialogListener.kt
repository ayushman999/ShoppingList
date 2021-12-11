package com.ayushman999.shoppinglist.ui.shoppinglist

import com.ayushman999.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}
