package com.example.routetask.ui.home.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.routetask.databinding.FragmentHomeBinding
import com.example.routetask.ui.home.viewmodel.HomeViewModel
import com.example.weatherwise.util.ChecksManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {


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

        // Inflate the layout for this fragment
        return homeBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBinding.prHome.visibility = View.VISIBLE

        if (ChecksManager.checkConnection(requireContext())) {
            displayData(requireContext())
        } else {
            Toast.makeText(
                requireContext(),
                "Check Your Internet Connection",
                Toast.LENGTH_LONG
            ).show()
        }

        setupSearchFunctionality()

    }

    private fun displayData(context: Context) {
        homeAdapter = HomeAdapter(context)
        homeBinding.rvProducts.adapter = homeAdapter
        homeBinding.rvProducts.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        lifecycleScope.launch {
            homeViewModel.allProducts.collect { result ->
                result.fold(
                    onSuccess = { productsList ->
                        homeAdapter.submitList(productsList.products)
                        homeBinding.prHome.visibility = View.GONE
                    },
                    onFailure = { error ->
                        Toast.makeText(
                            context,
                            "Failed to fetch products: ${error.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            }
        }

    }

    private fun setupSearchFunctionality() {
        homeBinding.etSearchField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                homeAdapter.filter.filter(s)
            }
        })
    }


}

