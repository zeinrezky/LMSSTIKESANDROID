package com.example.lmsstikes.view.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.TabAdapter
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tab.*

class TabFragment: BaseFragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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