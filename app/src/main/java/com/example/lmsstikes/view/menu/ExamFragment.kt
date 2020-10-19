package com.example.lmsstikes.view.menu

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.AttendanceAdapter
import com.example.lmsstikes.adapter.ExamAdapter
import com.example.lmsstikes.adapter.ListScheduleAdapter
import com.example.lmsstikes.databinding.FragmentExamBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Exam
import com.example.lmsstikes.model.Schedule
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_exam.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject

class ExamFragment: BaseFragment(){

    private lateinit var binding: FragmentExamBinding
    private val viewModel by inject<MenuViewModel>()
    private var isShown = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam, container, false)
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
            listSchedule.observe(viewLifecycleOwner, Observer {
                if (isShown)
                    showDialog(it)
                else {
                    isShown = true
                    viewModel.period.value = it[it.lastIndex].name
                    viewModel.getListExam(it[it.lastIndex].id)
                }
            })
            listExam.observe(viewLifecycleOwner, Observer {
                setListExam(it)
            })
            clickPeriod.observe(viewLifecycleOwner, Observer {
                setView()
            })

        }
        if (arguments?.getBoolean(CourseFragment.ARG_IS_TOOLBAR_VISIBLE)!!){
            setView()
            toolbar.visibility = View.VISIBLE
        } else {
            toolbar.visibility = View.GONE
        }
        setView()
    }

    private fun setView(){
        setToolbar(getString(R.string.exam))
        setNavigation()
        viewModel.getListSchedule()
    }
    private fun setListExam(list: ArrayList<Exam>) {
        rv_exam.layoutManager = LinearLayoutManager(context)
        rv_exam.adapter = activity?.let {
            ExamAdapter(it, list)
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
            viewModel.getListExam(list[position].id)
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
        const val ARG_IS_TOOLBAR_VISIBLE = "toolbar"


        fun newInstance(isVisible: Boolean): ExamFragment {
            val fragment = ExamFragment()

            val bundle = Bundle().apply {
                putBoolean(ARG_IS_TOOLBAR_VISIBLE, isVisible)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

}