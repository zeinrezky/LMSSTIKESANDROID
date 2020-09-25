package com.example.lmsstikes.view.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.lmsstikes.R
import com.example.lmsstikes.databinding.FragmentAllMenuBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_all_menu.*
import org.koin.android.ext.android.inject

class AllMenuFragment : BaseFragment(){

    private lateinit var binding: FragmentAllMenuBinding
    private val viewModel by inject<MenuViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_menu, container, false)
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
                UtilityHelper.snackbarLong(view_parent, getString(R.string.error_network))
            })
            isLoading.observe(viewLifecycleOwner, Observer { bool ->
                bool.let { loading ->
                    if(loading){ showWaitingDialog() }
                    else { hideWaitingDialog() }
                }
            })
            clickSchedule.observe(viewLifecycleOwner, Observer {
                addFragment(TabFragment.newInstance())
            })
            clickCourse.observe(viewLifecycleOwner, Observer {
                addFragment(ExamFragment.newInstance(true))
            })
            clickForum.observe(viewLifecycleOwner, Observer {
                addFragment(ForumFragment.newInstance())
            })
            clickAttendance.observe(viewLifecycleOwner, Observer {
                addFragment(AttendanceFragment.newInstance())
            })
            clickScore.observe(viewLifecycleOwner, Observer {
                addFragment(ScoreFragment.newInstance())
            })

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllMenuFragment()
    }
}