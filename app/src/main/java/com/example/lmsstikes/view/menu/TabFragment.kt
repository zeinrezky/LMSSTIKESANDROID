package com.example.lmsstikes.view.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.TabAdapter
import com.example.lmsstikes.databinding.FragmentTabBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tab.*
import org.koin.android.ext.android.inject

class TabFragment: BaseFragment(){

    private lateinit var binding: FragmentTabBinding
    private val viewModel by inject<MenuViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        with(viewModel){
            snackbarMessage.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Int -> UtilityHelper.snackbarLong(view_parent, getString(it))
                    is String -> UtilityHelper.snackbarLong(view_parent, it)
                }
            })
            networkError.observe(viewLifecycleOwner, Observer {
                UtilityHelper.snackbarLong(view_parent, getString(com.example.lmsstikes.R.string.error_network))
            })
            isLoading.observe(viewLifecycleOwner, Observer { bool ->
                bool.let { loading ->
                    if(loading){ showWaitingDialog() }
                    else { hideWaitingDialog() }
                }
            })
            clickScheduleDetail.observe(viewLifecycleOwner, Observer {
                addFragment(ScheduleDetailFragment.newInstance())
            })
        }
        setView()
    }

    private fun setView(){
        setToolbar(getString(R.string.schedule))
        viewPager.adapter = TabAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TabFragment()
    }
}