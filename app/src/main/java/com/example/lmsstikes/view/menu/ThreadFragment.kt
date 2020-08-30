package com.example.lmsstikes.view.menu

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.ThreadAdapter
import com.example.lmsstikes.databinding.FragmentThreadBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Thread
import com.example.lmsstikes.view.base.BaseFragment
import com.example.lmsstikes.view.dashboard.DetailFragment
import kotlinx.android.synthetic.main.fragment_thread.*
import org.koin.android.ext.android.inject

class ThreadFragment: BaseFragment(), ThreadAdapter.Listener{

    private lateinit var binding: FragmentThreadBinding
    private val viewModel by inject<MenuViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_thread, container, false)
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

        }
        setView()
    }

    private fun setView(){
        viewModel.getListThread(arguments!!.getInt(ARG_ID))
        viewModel.courseName.value = arguments?.getString(ARG_COURSE_NAME)
        viewModel.sessionName.value = arguments?.getString(ARG_SESSION_NAME)
        setToolbar(getString(R.string.thread))
    }
    private fun setListThread(list: ArrayList<Thread.ThreadList>) {
        rv_thread.layoutManager = LinearLayoutManager(context)
        rv_thread.adapter = activity?.let {
            ThreadAdapter(it, list, this)
        }
    }

    companion object {

        const val ARG_ID = "id"
        const val ARG_COURSE_NAME = "course_name"
        const val ARG_SESSION_NAME = "session_name"


        fun newInstance(id: Int, courseName: String, sessionName: String): ThreadFragment {
            val fragment = ThreadFragment()

            val bundle = Bundle().apply {
                putInt(ARG_ID, id)
                putString(ARG_COURSE_NAME, courseName)
                putString(ARG_SESSION_NAME, sessionName)
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

    override fun onItemClicked(data: Thread.ThreadList) {
        addFragment(ThreadViewFragment.newInstance(data))
    }

}