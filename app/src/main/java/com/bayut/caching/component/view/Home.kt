package com.bayut.caching.component.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bayut.caching.callbacks.OnRecyclerItemClickCallback
import com.bayut.caching.component.adapter.ImageListAdapter
import com.bayut.caching.component.model.ResultsItem
import com.bayut.caching.component.viewmodel.HomeViewModel
import com.bayut.caching.databinding.HomeBinding
import com.bayut.caching.service.Result
import com.bayut.caching.utility.AppUtils
import dagger.hilt.android.AndroidEntryPoint


/**
 * [Home]
 */
@AndroidEntryPoint
class Home : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    OnRecyclerItemClickCallback<ResultsItem> {

    private lateinit var adapter: ImageListAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ImageListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        binding.rvImages.layoutManager =
            linearLayoutManager
        binding.rvImages.adapter = adapter
        getImages()
    }

    private fun getImages() {
        viewModel.getImages()
            .observe(viewLifecycleOwner, { result ->
                when (result) {
                    is Result.Success -> {
                        binding.srlImages.isRefreshing = false
                        result.data.let {
                            adapter.submitList(result.data.results)
                        }
                    }
                    is Result.Loading -> {
                        binding.srlImages.isRefreshing = true
                    }
                    is Result.Error -> {
                        binding.srlImages.isRefreshing = false
                        AppUtils.shortToast(requireContext(), result.message)
                    }
                }
            })
    }

    override fun onRefresh() {
        getImages()
    }

    override fun onRecyclerItemClicked(position: Int, view: View, data: ResultsItem) {
        val action = HomeDirections.actionHomeToContent(data)
        navController.navigate(action)
    }

}