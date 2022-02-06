package com.example.kotlin2_l1.presentation.ui.fragments.shopListFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2_l1.R
import com.example.kotlin2_l1.databinding.FragmentShopListBinding
import com.example.kotlin2_l1.presentation.ui.activities.main.MainViewModel
import com.example.kotlin2_l1.presentation.ui.activities.main.ShopItemListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopListFragment : Fragment() {

    private lateinit var binding: FragmentShopListBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ShopItemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(activity, "Fragment ShopList", Toast.LENGTH_SHORT).show()

        initObservers()
        setupRv()
        initListeners()
    }

    private fun initListeners() {
        adapter.onShopItemLongClickListener = {
            viewModel.editShopItem(it)
        }

        binding.btnAdd.setOnClickListener{
            // TODO open: edit fragment
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView?

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0.let {
                    viewModel.getShopItemByName(p0.toString())
                    viewModel.shopItem.observe( this@ShopListFragment, {
                        Toast.makeText(
                            activity,
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
        return super.onCreateOptionsMenu(menu, inflater)
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