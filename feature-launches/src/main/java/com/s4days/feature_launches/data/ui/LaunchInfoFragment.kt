package com.s4days.feature_launches.data.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import com.s4days.core_network.data.model.SpaceXLaunch
import com.s4days.feature_launches.R
import com.s4days.feature_launches.data.di.LaunchesModule
import com.s4days.feature_launches.data.ui.viewModel.LaunchesViewModel
import com.s4days.feature_launches.databinding.FragmentLaunchInfoBinding
import com.squareup.picasso.Picasso
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import java.text.DateFormat
import java.util.*

internal class LaunchInfoFragment : Fragment(R.layout.fragment_launch_info) {

    companion object {
        val LAUNCH_ID = "launchId"
    }

    private val viewModelFactory: ViewModelProvider.NewInstanceFactory by inject()
    private val viewModel by viewModels<LaunchesViewModel> { viewModelFactory }

    private lateinit var binding: FragmentLaunchInfoBinding
    private lateinit var playerFragment: YouTubePlayerSupportFragmentX
    private lateinit var launchId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchId = requireActivity().intent.getStringExtra(LAUNCH_ID)!!

        initDependency()
        initViews(view)
        subscribeViewModel()

    }

    private fun initViews(view: View) {
        binding = FragmentLaunchInfoBinding.bind(view)
        playerFragment = YouTubePlayerSupportFragmentX.newInstance()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = ""

        parentFragmentManager.beginTransaction().apply {
            add(R.id.player, playerFragment)
            commit()
        }
    }

    private fun initDependency() {
        KTP.openScope(requireActivity().applicationContext).installModules(
            LaunchesModule()
        ).inject(this)
    }

    private fun setLaunchInfo(launch: SpaceXLaunch) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = launch.name
        val df = DateFormat.getDateTimeInstance()

        binding.name.text = launch.name
        binding.date.text = launch.dateUnix?.let { df.format(Date(it * 1000)) }
        binding.details.text = launch.details
        if (viewModel.favorites.value?.contains(launchId) == true)
            binding.favorite.setImageResource(R.drawable.ic_fav_checked)
        else
            binding.favorite.setImageResource(R.drawable.ic_fav_uncheck)

        binding.favorite.setOnClickListener {
            if (viewModel.favorites.value?.contains(launchId) == true) {
                binding.favorite.setImageResource(R.drawable.ic_fav_uncheck)
                viewModel.deleteFavorite(launchId)
            } else {
                binding.favorite.setImageResource(R.drawable.ic_fav_checked)
                viewModel.addFavorite(launchId)
            }
        }

        launch.links?.patch?.small?.let {
            Picasso.get().load(it).into(binding.image)
        }
        launch.links?.youtubeId?.let { it1 -> initYoutube(it1) }
    }

    private fun subscribeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (!it) {
                viewModel.getLaunchInfo(launchId)?.let { launch ->
                    setLaunchInfo(launch)
                }
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.getLaunches()
    }

    private fun initYoutube(id: String) {
        playerFragment.initialize(
            getString(R.string.youtube_api_key),
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.cueVideo(id)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Error initialization YouTube",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
    }

}