package com.example.lmsstikes.view.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.SessionAdapter
import com.example.lmsstikes.databinding.FragmentSessionBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Session
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_session.*
import kotlinx.android.synthetic.main.fragment_session.view_parent
import org.koin.android.ext.android.inject

class SessionFragment : BaseFragment() {

    private lateinit var binding: FragmentSessionBinding
    private val viewModel by inject<MenuViewModel>()
    private var arrayListSession = arrayListOf<Session>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_session, container, false)
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
            listSession.observe(viewLifecycleOwner, Observer {
                arrayListSession = it
                setList()
            })
            listTopic.observe(viewLifecycleOwner, Observer {
                for (id in arrayListSession.indices){
                    for (id_session in it.indices){
                        if (arrayListSession[id].id == it[id_session].id_session.toInt())
                            arrayListSession[id].topic = it
                    }
                }
                setListSession()

            })
        }
        setView()
    }

    private fun setView() {

        setToolbar(getString(R.string.session))
        setNavigation()
        viewModel.getListSession(arguments!!.getInt(ARG_ID))
        viewModel.courseName.value = arguments?.getString(ARG_NAME)
        viewModel.courseCode.value = arguments?.getString(ARG_CODE)
        viewModel.courseType.value = arguments?.getString(ARG_TYPE)
        viewModel.courseClass.value = arguments?.getString(ARG_CLASS)
    }
    private fun setList(){
        for (id in arrayListSession.indices){
            viewModel.getListTopic(arrayListSession[id].id)
        }
    }

    private fun setListSession() {

        Log.d("session", arrayListSession.toString())
        rv_session.layoutManager = LinearLayoutManager(context)
        rv_session.adapter = activity?.let {
            SessionAdapter(it, arrayListSession)
        }
    }

    companion object {
        const val ARG_ID = "id"
        const val ARG_MONTH = "month"
        const val ARG_NAME = "name"
        const val ARG_CODE = "code"
        const val ARG_TYPE = "type"
        const val ARG_CLASS = "class"


        fun newInstance(
            id: Int,
            month: Int,
            name: String,
            code: String,
            type: String,
            courseClass: String
        ): SessionFragment {
            val fragment = SessionFragment()

            val bundle = Bundle().apply {
                putInt(ARG_ID, id)
                putInt(ARG_MONTH, month)
                putString(ARG_NAME, name)
                putString(ARG_CODE, code)
                putString(ARG_TYPE, type)
                putString(ARG_CLASS, courseClass)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

}