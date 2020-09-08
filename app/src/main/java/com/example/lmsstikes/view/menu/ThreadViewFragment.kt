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
            clickRemove.observe(viewLifecycleOwner, Observer {
                viewModel.deleteThread(Thread.Delete(arguments?.getInt(ARG_ID_THREAD)!!))
            })

        }
        setView()
    }

    private fun setView(){
        viewModel.userName.value = arguments?.getString(ARG_NAME)
        viewModel.userType.value = arguments?.getString(ARG_TYPE)
        viewModel.datePost.value = UtilityHelper.getSdfDayMonthYearTime(arguments?.getString(ARG_DATE))
        viewModel.threadTitle.value = arguments?.getString(ARG_TITLE)
        viewModel.threadContent.value = arguments?.getString(ARG_CONTENT)

        UtilityHelper.setImage(context!!, arguments?.getString(ARG_IMG)!!, avatar)

        setNavigation()
        setToolbar(arguments?.getString(ARG_TITLE)!!)

    }

    companion object {

        const val ARG_ID = "id"
        const val ARG_ID_THREAD = "id_thread"
        const val ARG_NAME = "name"
        const val ARG_TYPE = "type"
        const val ARG_CONTENT = "content"
        const val ARG_TITLE = "title"
        const val ARG_DATE = "date"
        const val ARG_IMG = "img"


        fun newInstance(data: Thread.ThreadList): ThreadViewFragment {
            val fragment = ThreadViewFragment()

            val bundle = Bundle().apply {
                putInt(ARG_ID, data.id_session)
                putInt(ARG_ID_THREAD, data.id_thread)
                putString(ARG_NAME, data.poster_name)
                putString(ARG_TYPE, data.role)
                putString(ARG_CONTENT, data.content)
                putString(ARG_TITLE, data.title)
                putString(ARG_DATE, data.date_post)
                putString(ARG_IMG, data.img)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

}