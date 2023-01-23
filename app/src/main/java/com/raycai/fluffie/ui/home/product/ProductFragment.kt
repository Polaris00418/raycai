package com.raycai.fluffie.ui.home.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dam.bestexpensetracker.util.AppLog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.raycai.fluffie.HomeActivity
import com.raycai.fluffie.R
import com.raycai.fluffie.base.BaseFragment
import com.raycai.fluffie.databinding.FragmentProductBinding
import com.raycai.fluffie.ui.home.product.claims.ClaimsFragment
import com.raycai.fluffie.ui.home.product.reviews.ReviewsFragment
import com.raycai.fluffie.ui.home.product.summaries.SummariesFragment

class ProductFragment : BaseFragment() {

    private val TAG = ProductFragment::class.java.simpleName
    private lateinit var viewModel: ProductViewModel
    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        binding = FragmentProductBinding.inflate(inflater)
        binding.fragment = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        setObservers()
        initData()
        return binding.root
    }

    private fun setObservers() {
        viewModel.tabsChanged.observeForever {
            binding.tabLayout.removeAllTabs()
            viewModel.tabs.forEach {
                val tab: TabLayout.Tab = binding.tabLayout.newTab()
                tab.text = it
                binding.tabLayout.addTab(tab, true)
            }
            binding.tabLayout.getTabAt(0)?.select()
        }

        viewModel.product.observeForever {
            if (it != null) {
                binding.ivProductImg.setImageResource(it.imgRes)
                binding.tvTitle.text = it.name

            }
        }
    }

    private fun initData() {
        viewModel.initData()
        viewModel.product.postValue((activity as HomeActivity).selectedProduct)

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "Claims" -> {
                        loadClaimsFragment()
                    }
                    "Summaries" -> {
                        loadSummariesFragment()
                    }
                    "Our reviews" -> {
                        loadReviewsFragment()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                log("onTabUnselected()")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                log("onTabReselected()")
            }
        })
    }

    fun onShareThroughClicked(view: View) {
        (activity as HomeActivity).loadReviewFragment(true)
    }

    fun onHartClicked(view: View) {

    }

    fun onShareClicked(view: View) {

    }

    fun onMarketClicked(view: View) {

    }

    fun onBackPressed(view: View) {
        (activity as HomeActivity).onBackPressed()
    }

    private fun loadReviewsFragment() {
        if (getCurrentFragment() !is ReviewsFragment) {
            loadFragment(ReviewsFragment())
        }
    }

    private fun loadSummariesFragment() {
        if (getCurrentFragment() !is SummariesFragment) {
            loadFragment(SummariesFragment())
        }
    }

    private fun loadClaimsFragment() {
        if (getCurrentFragment() !is ClaimsFragment) {
            loadFragment(ClaimsFragment())
        }
    }

    private fun getCurrentFragment(): Fragment? {
        return childFragmentManager.findFragmentById(R.id.fragmentContainer)
    }

    private fun loadFragment(fragment: Fragment) {
        var ft = childFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.fragment_enter,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fragment_exit
            ).replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun log(msg: String) {
        AppLog.log(TAG, msg)
    }



}