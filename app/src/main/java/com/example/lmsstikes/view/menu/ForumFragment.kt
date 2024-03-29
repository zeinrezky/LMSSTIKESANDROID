package com.example.lmsstikes.view.menu

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.CourseAdapter
import com.example.lmsstikes.adapter.ListScheduleAdapter
import com.example.lmsstikes.databinding.FragmentForumBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Course
import com.example.lmsstikes.model.Schedule
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forum.*
import org.koin.android.ext.android.inject

class ForumFragment: BaseFragment(), CourseAdapter.Listener{

    private lateinit var binding: FragmentForumBinding
    private val viewModel by inject<MenuViewModel>()
    private var isShown = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forum, container, false)
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
            listSchedule.observe(viewLifecycleOwner, Observer { list ->
                val listSchedule = list.filter {
                    it.status == "AKTIF"
                }
                if (isShown)
                    showDialog(listSchedule as ArrayList<Schedule>)
                else {
                    isShown = true
                    viewModel.period.value = listSchedule[listSchedule.lastIndex].name
                    viewModel.getListCourse(listSchedule[listSchedule.lastIndex].id)
                }
            })
            listCourse.observe(viewLifecycleOwner, Observer {
                setListCourse(it)
            })
            clickPeriod.observe(viewLifecycleOwner, Observer {
                viewModel.getListSchedule()
            })

        }
        setView()
    }

    override fun onResume() {
        super.onResume()
        isShown = false
        viewModel.getListSchedule()

    }
    private fun setView(){
        setToolbar(getString(R.string.forum))
        setNavigation()
    }
    private fun setListCourse(list: ArrayList<Course>) {
        rv_course.layoutManager = LinearLayoutManager(context)
        rv_course.adapter = activity?.let {
            CourseAdapter(it, list, this)
        }

        val latestCourse = arrayListOf<Course>()
        if (list.isNullOrEmpty().not())
            latestCourse.add(list[list.lastIndex])
        rv_latest.layoutManager = LinearLayoutManager(context)
        rv_latest.adapter = activity?.let {
            CourseAdapter(it, latestCourse, this)
        }
    }
    private fun showDialog(list: ArrayList<Schedule>) {
        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.dialog_schedule)

        val listView = dialog?.findViewById(R.id.list_schedule) as ListView

        val listSchedule = arrayListOf<String>()
        for (id in list.indices){
            listSchedule.add(list[id].name)
        }
        listView.adapter = ListScheduleAdapter(list)
        listView.setOnItemClickListener { parent, view, position, id ->
            viewModel.getListCourse(list[position].id)
            viewModel.period.value = list[position].name
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
        @JvmStatic
        fun newInstance() = ForumFragment()
    }

    override fun onItemClicked(data: Course) {
        isShown = false
        addFragment(ForumSessionFragment.newInstance(data.id, 3, data.name, data.code, data.type, data.course_class))
    }

}