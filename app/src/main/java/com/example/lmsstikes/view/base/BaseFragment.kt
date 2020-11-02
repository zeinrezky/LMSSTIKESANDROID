package com.example.lmsstikes.view.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lmsstikes.R
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.view.menu.TabFragment
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.concurrent.fixedRateTimer

open class BaseFragment : Fragment() {

    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialog()
    }

    fun setToolbar(title: String) {
        toolbar_title.text = title
    }

    fun setNavigation(){
        toolbar_back.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
            AppPreference.putShown(false)
        }
    }

    fun addFragment(fragment: Fragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .addToBackStack(null)
            .commit()
    }

    private fun initDialog() {
        dialog = Dialog(context!!)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_waiting)
        dialog.setCancelable(false)
    }

    fun showWaitingDialog() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun hideWaitingDialog() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}