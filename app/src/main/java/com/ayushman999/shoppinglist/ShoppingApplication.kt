package com.ayushman999.shoppinglist

import android.app.Application
import com.ayushman999.shoppinglist.data.db.ShoppingDatabase
import com.ayushman999.shoppinglist.data.repository.ShoppingRepository
import com.ayushman999.shoppinglist.ui.shoppinglist.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApplication: Application(), KodeinAware{
    override val kodein: Kodein= Kodein.lazy {
        import(androidXModule(this@ShoppingApplication))
        bind() from singleton { ShoppingDatabase(instance()) }
        bind() from singleton { ShoppingRepository(instance()) }
    //instance will look for database and provide it to the repository
        bind() from provider { ShoppingViewModelFactory(instance()) }
    }

    /*giving context from onCreate of any activity is not a great practise therefore we use application
    to instantiate databases*/
}