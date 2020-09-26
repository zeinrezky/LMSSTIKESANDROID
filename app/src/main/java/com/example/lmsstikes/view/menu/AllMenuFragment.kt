package com.example.lmsstikes.view.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.lmsstikes.R
import com.example.lmsstikes.databinding.FragmentAllMenuBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.view.base.BaseFragment
import com.example.lmsstikes.view.login.LoginActivity
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
            clickLogout.observe(viewLifecycleOwner, Observer {
                showLogoutDialog()
            })

        }
    }

    private fun showLogoutDialog(){
        val dialog = AlertDialog.Builder(context!!)
        dialog.setTitle(getString(R.string.confirmation))
        dialog.setMessage(getString(R.string.question))
        dialog.setNegativeButton(getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }
        dialog.setPositiveButton(getString(R.string.yes)) { dialog, which ->
            backToLogin()
        }
        dialog.create()
        dialog.show()
    }

    private fun backToLogin() {
        AppPreference.deleteAll()
        Toast.makeText(context, getString(R.string.success_logout), Toast.LENGTH_LONG).show()
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllMenuFragment()
    }
}