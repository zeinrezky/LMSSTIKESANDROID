package com.example.lmsstikes.view.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.ThreadReplyAdapter
import com.example.lmsstikes.databinding.FragmentThreadReplyBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Thread
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.util.Constant
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_thread_reply.*
import org.koin.android.ext.android.inject

class ThreadReplyFragment : BaseFragment(), ThreadReplyAdapter.Listener {

    private lateinit var binding: FragmentThreadReplyBinding
    private val viewModel by inject<MenuViewModel>()
    private var isQuoteVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_thread_reply, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        with(viewModel) {
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
                    if (loading) {
                        showWaitingDialog()
                    } else {
                        hideWaitingDialog()
                    }
                }
            })
            listThread.observe(viewLifecycleOwner, Observer {
                setListThread(it)
            })
            responseSuccess.observe(viewLifecycleOwner, Observer {
                message.setText("")
                layout_quote.visibility = View.GONE
                viewModel.getListThread(AppPreference.getThreadData().id_session)
            })
            clickSend.observe(viewLifecycleOwner, Observer {
                if(isQuoteVisible) {
                    viewModel.createThread(
                        Thread.Create(
                            AppPreference.getLoginData().id_user,
                            AppPreference.getThreadData().id_session,
                            AppPreference.getThreadData().id_thread,
                            AppPreference.getThreadData().id_thread,
                            AppPreference.getThreadData().title,
                            message.text.toString(),
                            Constant.Thread.OPEN,
                            Constant.Thread.REPLY,
                            content_desc.text.toString()
                        )
                    )
                }
                else {
                    viewModel.createThread(
                        Thread.Create(
                            AppPreference.getLoginData().id_user,
                            AppPreference.getThreadData().id_session,
                            AppPreference.getThreadData().id_thread,
                            AppPreference.getThreadData().id_thread,
                            AppPreference.getThreadData().title,
                            message.text.toString(),
                            Constant.Thread.OPEN,
                            Constant.Thread.REPLY,
                            ""
                        )
                    )
                }

            })
            clickClose.observe(viewLifecycleOwner, Observer {
                layout_quote.visibility = View.GONE
                isQuoteVisible = false
            })

        }
        setView()
    }

    private fun setView() {
        setToolbar(AppPreference.getThreadData().title)
        setNavigation()
        viewModel.getListThread(AppPreference.getThreadData().id_session)
        if (arguments?.getBoolean(ARG_IS_QUOTE)!!){
            setQuoteView(AppPreference.getThreadData())
        }
        if (AppPreference.getThreadData().status == Constant.Thread.LOCKED){
            message.visibility = View.GONE
            btn_send.visibility = View.GONE
        }

    }

    private fun setQuoteView(content: Thread.ThreadList){
        if (AppPreference.getThreadData().status == Constant.Thread.OPEN){
            isQuoteVisible = true
            layout_quote.visibility = View.VISIBLE
            content_desc.text = content.content
            usertype.text = content.role
            datepost.text = UtilityHelper.getSdfDayMonthYearTime(content.date_post)
            username.text = content.poster_name
            UtilityHelper.setImage(context!!, content.img, img)
        } else {
            isQuoteVisible = false
            layout_quote.visibility = View.GONE
        }

    }

    private fun setListThread(list: ArrayList<Thread.ThreadList>) {
        val replyThreadList = arrayListOf<Thread.ThreadList>()
        for (id in list.indices){
            if (list[id].type == Constant.Thread.REPLY)
                replyThreadList.add(list[id])
        }
        rv_thread_reply.layoutManager = LinearLayoutManager(context)
        rv_thread_reply.adapter = activity?.let {
            ThreadReplyAdapter(it, replyThreadList, this)
        }
    }

    companion object {

        const val ARG_IS_QUOTE = "is_quote"


        fun newInstance(is_quote: Boolean): ThreadReplyFragment {
            val fragment = ThreadReplyFragment()

            val bundle = Bundle().apply {
                putBoolean(ARG_IS_QUOTE, is_quote)

            }

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onBtnRemoveClick(id: Int) {
        viewModel.deleteThread(Thread.Delete(id))
    }

    override fun onQuoteClick(content: Thread.ThreadList) {
        setQuoteView(content)
    }

}