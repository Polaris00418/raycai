package com.raycai.fluffie.ui.home.product.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.bestexpensetracker.util.AppLog
import com.raycai.fluffie.HomeActivity
import com.raycai.fluffie.R
import com.raycai.fluffie.adapter.UserReviewAdapter
import com.raycai.fluffie.base.BaseFragment
import com.raycai.fluffie.data.model.UserReview
import com.raycai.fluffie.databinding.FragmentReviewsBinding
import com.raycai.fluffie.ui.home.product.claims.ClaimsFragment

class ReviewsFragment : BaseFragment(), UserReviewAdapter.Listener {

    private val TAG = ClaimsFragment::class.java.simpleName
    private lateinit var viewModel: ReviewsViewModel
    private lateinit var binding: FragmentReviewsBinding

    private var colorGray = 0
    private var colorWhite = 0

    private lateinit var adapter: UserReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[ReviewsViewModel::class.java]
        binding = FragmentReviewsBinding.inflate(inflater)
        binding.fragment = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        setObservers()
        initData()
        return binding.root
    }

    private fun setObservers() {
        viewModel.reviewChanged.observeForever {
            adapter.notifyDataSetChanged()
        }

        viewModel.selectedFilterType.observeForever {
            if (it != null) {
                resetFilters()
                selectFilter(it)
            }
        }
    }

    private fun initData() {
        colorGray = ContextCompat.getColor(requireContext(), R.color.disable_gray)
        colorWhite = ContextCompat.getColor(requireContext(), R.color.white)

        adapter = UserReviewAdapter(viewModel.reviews)
        adapter.listener = this
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.initData()
    }

    fun onFilterAllClicked(view: View) {
        viewModel.onFilterSelected(ReviewsViewModel.FilterType.ALL)
    }

    fun onFilterTrendingClicked(view: View) {
        viewModel.onFilterSelected(ReviewsViewModel.FilterType.TRENDING)
    }

    fun onFilterVideoClicked(view: View) {
        viewModel.onFilterSelected(ReviewsViewModel.FilterType.VIDEO)
    }

    fun onFilterAudioClicked(view: View) {
        viewModel.onFilterSelected(ReviewsViewModel.FilterType.AUDIO)
    }

    private fun selectFilter(ft: ReviewsViewModel.FilterType) {
        when (ft) {
            ReviewsViewModel.FilterType.ALL -> {
                binding.tvFilterAll.setTextColor(colorWhite)
            }
            ReviewsViewModel.FilterType.TRENDING -> {
                binding.tvFilterTrending.setTextColor(colorWhite)
            }
            ReviewsViewModel.FilterType.VIDEO -> {
                binding.tvVideoFilter.setTextColor(colorWhite)
                binding.ivFilterVideo.setColorFilter(colorWhite)
            }
            ReviewsViewModel.FilterType.AUDIO -> {
                binding.tvAudioFilter.setTextColor(colorWhite)
                binding.ivAudioFilter.setColorFilter(colorWhite)
            }
        }
    }

    private fun resetFilters() {
        binding.tvFilterAll.setTextColor(colorGray)
        binding.tvFilterTrending.setTextColor(colorGray)
        binding.tvVideoFilter.setTextColor(colorGray)
        binding.tvAudioFilter.setTextColor(colorGray)
        binding.ivFilterVideo.setColorFilter(colorGray)
        binding.ivAudioFilter.setColorFilter(colorGray)
    }

    override fun onUserReviewClicked(ur: UserReview) {
        (activity as HomeActivity).loadReviewExpandFragment(true)
    }

    private fun log(msg: String) {
        AppLog.log(TAG, msg)
    }

}