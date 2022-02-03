package com.example.kotlin2_l1.presentation.ui.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin2_l1.R
import com.example.kotlin2_l1.databinding.ActivityMainBinding
import com.example.kotlin2_l1.presentation.ui.activities.add_shopItem.AddShopItemActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(R.id.container)
    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: ShopItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservers()
        setupRv()
        initListeners()
    }

    private fun initListeners() {
        adapter.onShopItemLongClickListener = {
            viewModel.editShopItem(it)
        }

        binding.btnAdd.setOnClickListener{
            Intent(this, AddShopItemActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun initObservers() {
        viewModel.shopList.observe(this, {
            adapter.submitList(it)
        })
    }

    private fun setupRv() {
        adapter = ShopItemListAdapter()
        binding.mainRecycler.adapter = adapter
        setUpSwipeListener(binding.mainRecycler)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView?

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0.let {
                    viewModel.getShopItemByName(p0.toString())
                    viewModel.shopItem.observe(this@MainActivity, {
                        Toast.makeText(
                            this@MainActivity,
                            it.toString(),
                            Toast.LENGTH_SHORT).show()
                    })
                }
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpSwipeListener(rv: RecyclerView){
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                viewModel.deleteShopItem(adapter.currentList[viewHolder.absoluteAdapterPosition])
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }
}