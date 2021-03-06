package com.s4days.feature_launches.data.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.s4days.feature_launches.R
import com.s4days.feature_launches.data.di.LaunchesModule
import com.s4days.feature_launches.data.ui.LaunchInfoFragment.Companion.LAUNCH_ID
import com.s4days.feature_launches.data.ui.adapter.LaunchAdapter
import com.s4days.feature_launches.data.ui.viewModel.LaunchesViewModel
import com.s4days.feature_launches.databinding.FragmentLaunchListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

internal class LaunchListFragment : Fragment(R.layout.fragment_launch_list) {

    private val viewModelFactory: ViewModelProvider.NewInstanceFactory by inject()
    private val viewModel by viewModels<LaunchesViewModel> { viewModelFactory }

    private lateinit var binding: FragmentLaunchListBinding

    private val adapter by lazy {
        LaunchAdapter(
            this::openLaunchInfo,
            this::favorite,
            this::isFavorite
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDependency()
        initViews(view)
        subscribeViewModel()

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        GlobalScope.launch {
            viewModel.updateFavorites()
            requireActivity().runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun initDependency() {
        KTP.openScope(requireActivity().applicationContext).installModules(
            LaunchesModule()
        ).inject(this)
    }

    private fun initViews(rootView: View) {
        binding = FragmentLaunchListBinding.bind(rootView)
        binding.launchList.adapter = adapter

        binding.refresh.setOnRefreshListener {
            viewModel.getLaunches()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun subscribeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.refresh.isRefreshing = it
            if (!it) {
                viewModel.launches.value?.let { it1 -> adapter.updateList(it1) }
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.favorites.observe(viewLifecycleOwner, Observer { adapter.notifyDataSetChanged() })
        viewModel.getLaunches()
    }

    private fun openLaunchInfo(id: String){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.deeplink_scheme) + "launchinfo")).apply {
            putExtra(LAUNCH_ID, id)
        })
    }

    private fun favorite(id: String){
        if (isFavorite(id))
            viewModel.deleteFavorite(id)
        else
            viewModel.addFavorite(id)
    }

    private fun isFavorite(id: String): Boolean{
        return viewModel.favorites.value?.contains(id) == true
    }

}