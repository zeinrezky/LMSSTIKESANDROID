package com.example.lmsstikes.view.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.ForumSessionAdapter
import com.example.lmsstikes.databinding.FragmentForumSessionBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Session
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forum_session.*
import org.koin.android.ext.android.inject

class ForumSessionFragment : BaseFragment(), ForumSessionAdapter.Listener {

    private lateinit var binding: FragmentForumSessionBinding
    private val viewModel by inject<MenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forum_session, container, false)
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
                setListSession(it)
            })
        }
        setView()
    }

    private fun setView() {

        viewModel.getListSession(arguments!!.getInt(ARG_ID), arguments!!.getInt(ARG_MONTH))
        viewModel.courseName.value = arguments?.getString(ARG_NAME)
        viewModel.courseCode.value = arguments?.getString(ARG_CODE)
        viewModel.courseType.value = arguments?.getString(ARG_TYPE)
        viewModel.courseClass.value = arguments?.getString(ARG_CLASS)
    }


    private fun setListSession(list: ArrayList<Session>) {

        rv_session.layoutManager = LinearLayoutManager(context)
        rv_session.adapter = activity?.let {
            ForumSessionAdapter(it, list, this)
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
        ): ForumSessionFragment {
            val fragment = ForumSessionFragment()

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

    private fun addFragment(fragment: Fragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .addToBackStack(null)
            .commit()
    }

    override fun onItemClicked(data: Session) {
        addFragment(ThreadFragment.newInstance(data.id, arguments?.getString(ARG_NAME)!!, data.name))
    }

}