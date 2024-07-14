package com.example.routetask.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.routetask.R
import com.example.routetask.databinding.FragmentHomeBinding
import com.example.routetask.network.ProductRemoteDataSourceImpl
import com.example.weatherwise.util.ChecksManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)


        if (ChecksManager.checkConnection(requireContext())) {
            displayData(requireContext())
        } else {
            Toast.makeText(requireContext(), "Check Your Internet Connection", Toast.LENGTH_SHORT)
                .show()
        }

        // Inflate the layout for this fragment
        return homeBinding.root


    }

    private fun displayData(context: Context) {
        homeAdapter = HomeAdapter(context)
        homeBinding.rvProducts.adapter = homeAdapter
        homeBinding.rvProducts.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.allProducts.collect { productsList ->
                homeAdapter.submitList(productsList)
            }

        }
    }

}

