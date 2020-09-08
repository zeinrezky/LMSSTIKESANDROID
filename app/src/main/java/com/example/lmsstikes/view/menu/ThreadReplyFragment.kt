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
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_thread_reply.*
import org.koin.android.ext.android.inject

class ThreadReplyFragment: BaseFragment(), ThreadReplyAdapter.Listener{

    private lateinit var binding: FragmentThreadReplyBinding
    private val viewModel by inject<MenuViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_thread_reply, container, false)
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
            listThread.observe(viewLifecycleOwner, Observer {
                setListThread(it)
            })
            clickSend.observe(viewLifecycleOwner, Observer {

            })

        }
        setView()
    }

    private fun setView(){
        setToolbar(getString(R.string.thread))
        setNavigation()
        viewModel.getListThread(arguments!!.getInt(ARG_ID))
    }
    private fun setListThread(list: ArrayList<Thread.ThreadList>) {
        rv_thread_reply.layoutManager = LinearLayoutManager(context)
        rv_thread_reply.adapter = activity?.let {
            ThreadReplyAdapter(it, list, this)
        }
    }

    companion object {

        const val ARG_ID = "id"


        fun newInstance(id: Int): ThreadReplyFragment {
            val fragment = ThreadReplyFragment()

            val bundle = Bundle().apply {
                putInt(ARG_ID, id)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onBtnRemoveClick(id: Int) {
        viewModel.deleteThread(Thread.Delete(id))
    }

    override fun onQuoteClick(content: String) {
        TODO("Not yet implemented")
    }

}