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
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.util.Constant
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_thread_reply.*
import kotlinx.android.synthetic.main.fragment_thread_view.*
import kotlinx.android.synthetic.main.fragment_thread_view.avatar
import kotlinx.android.synthetic.main.fragment_thread_view.view_parent
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
            responseSuccess.observe(viewLifecycleOwner, Observer {
                activity!!.supportFragmentManager.popBackStack()
            })
            clickReply.observe(viewLifecycleOwner, Observer {
                addFragment(ThreadReplyFragment.newInstance(
                    false))
            })
            clickQuote.observe(viewLifecycleOwner, Observer {
                addFragment(ThreadReplyFragment.newInstance(
                    true))
            })
            clickRemove.observe(viewLifecycleOwner, Observer {
                viewModel.deleteThread(Thread.Delete(arguments?.getInt(ARG_ID_THREAD)!!))
            })
            clickLock.observe(viewLifecycleOwner, Observer {
                viewModel.updateThread(Thread.Update(
                    arguments?.getInt(ARG_ID_THREAD)!!,
                    arguments?.getString(ARG_TITLE)!!,
                    arguments?.getString(ARG_CONTENT)!!,
                    Constant.Thread.LOCKED,
                    ""))
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
        if (AppPreference.getLoginData().role == Constant.Role.DOSEN)
            btn_lock.visibility = View.VISIBLE
        else {
            btn_lock.visibility = View.GONE
        }

        if (AppPreference.getLoginData().id_user == arguments?.getInt(ARG_ID_POSTER))
            btn_remove.visibility = View.VISIBLE
        else {
            btn_remove.visibility = View.GONE
        }

    }

    companion object {

        const val ARG_ID = "id"
        const val ARG_ID_POSTER = "id_poster"
        const val ARG_ID_THREAD = "id_thread"
        const val ARG_NAME = "name"
        const val ARG_TYPE = "type"
        const val ARG_CONTENT = "content"
        const val ARG_TITLE = "title"
        const val ARG_DATE = "date"
        const val ARG_IMG = "img"
        const val ARG_STATUS = "status"


        fun newInstance(data: Thread.ThreadList): ThreadViewFragment {
            val fragment = ThreadViewFragment()
            AppPreference.putThreadData(data)
            val bundle = Bundle().apply {
                putInt(ARG_ID, data.id_session)
                putInt(ARG_ID_THREAD, data.id_thread)
                putInt(ARG_ID_POSTER, data.id_poster)
                putString(ARG_NAME, data.poster_name)
                putString(ARG_TYPE, data.role)
                putString(ARG_CONTENT, data.content)
                putString(ARG_TITLE, data.title)
                putString(ARG_DATE, data.date_post)
                putString(ARG_IMG, data.img)
                putString(ARG_STATUS, data.status)

            }

            fragment.arguments = bundle

            return fragment
        }
    }

}