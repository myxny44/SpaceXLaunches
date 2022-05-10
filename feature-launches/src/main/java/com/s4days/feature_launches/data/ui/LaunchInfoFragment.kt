package com.s4days.feature_launches.data.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.s4days.feature_launches.R
import com.s4days.feature_launches.data.di.LaunchesModule
import com.s4days.feature_launches.data.ui.viewModel.LaunchesViewModel
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

internal class LaunchInfoFragment: Fragment(R.layout.fragment_launch_info) {

    private val viewModelFactory: ViewModelProvider.NewInstanceFactory by inject()
    private val viewModel by viewModels<LaunchesViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDependency()

        viewModel.error.value?.let { Log.d("testinstance", it) }
    }

    private fun initDependency() {
        KTP.openScope(requireActivity().applicationContext).installModules(
            LaunchesModule()
        ).inject(this)
    }

}