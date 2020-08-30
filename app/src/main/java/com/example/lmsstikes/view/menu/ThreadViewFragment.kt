package com.example.lmsstikes.view.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.lmsstikes.R
import com.example.lmsstikes.databinding.FragmentThreadViewBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Thread
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_thread_view.*
import org.koin.android.ext.android.inject

class ThreadViewFragment: BaseFragment(){

    private lateinit var binding: FragmentThreadViewBinding
    private val viewModel by inject<MenuViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_thread_view, container, false)
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
            clickReply.observe(viewLifecycleOwner, Observer {
                addFragment(ThreadReplyFragment.newInstance(arguments?.getInt(ARG_ID)!!))
            })
            clickQuote.observe(viewLifecycleOwner, Observer {
            })

        }
        setView()
    }

    private fun setView(){
        viewModel.userName.value = "Dummy"
        viewModel.userType.value = "Dummy"
        viewModel.datePost.value = arguments?.getString(ARG_DATE)
        viewModel.threadTitle.value = arguments?.getString(ARG_TITLE)
        viewModel.threadContent.value = arguments?.getString(ARG_CONTENT)

        setToolbar(arguments?.getString(ARG_TITLE)!!)

    }

    companion object {

        const val ARG_ID = "id"
        const val ARG_CONTENT = "content"
        const val ARG_TITLE = "title"
        const val ARG_DATE = "date"



        fun newInstance(data: Thread.ThreadList): ThreadViewFragment {
            val fragment = ThreadViewFragment()

            val bundle = Bundle().apply {
                putInt(ARG_ID, data.id_session)
                putString(ARG_CONTENT, data.content)
                putString(ARG_TITLE, data.title)
                putString(ARG_DATE, data.date_post)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    private fun addFragment(fragment: Fragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .addToBackStack(null)
            .commit()
    }

}