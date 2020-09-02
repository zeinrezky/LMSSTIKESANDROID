package com.example.lmsstikes.view.menu

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.ListScheduleAdapter
import com.example.lmsstikes.adapter.ThreadAdapter
import com.example.lmsstikes.databinding.FragmentThreadBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Schedule
import com.example.lmsstikes.model.Thread
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.view.base.BaseFragment
import com.example.lmsstikes.view.dashboard.DetailFragment
import kotlinx.android.synthetic.main.fragment_thread.*
import org.koin.android.ext.android.inject
import org.w3c.dom.Text

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
            clickCreateThread.observe(viewLifecycleOwner, Observer {
                showDialog()
            })
            threadData.observe(viewLifecycleOwner, Observer {
//                addFragment(ThreadViewFragment.newInstance(it))
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

    private fun showDialog() {
        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.dialog_new_thread)

        val close = dialog?.findViewById(R.id.close) as ImageView
        val done = dialog.findViewById(R.id.done) as ImageView
        val sessionName = dialog.findViewById(R.id.session_name) as TextView
        val title = dialog.findViewById(R.id.input_subject) as EditText
        val message = dialog.findViewById(R.id.input_message) as EditText

        sessionName.text = arguments?.getString(ARG_SESSION_NAME)

        close.setOnClickListener {
            dialog.dismiss()
        }
        done.setOnClickListener {
            viewModel.createThread(Thread.Create(
                AppPreference.getLoginData().id_user,
                arguments!!.getInt(ARG_ID),
                0,
                0,
                title.text.toString(),
                message.text.toString(),
                "OPEN",
                "NEW THREAD"))
            dialog.dismiss()
        }

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER

        dialog.window?.attributes = lp
        dialog.show()
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