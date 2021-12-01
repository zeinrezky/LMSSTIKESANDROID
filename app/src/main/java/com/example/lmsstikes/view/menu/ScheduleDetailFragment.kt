package com.example.lmsstikes.view.menu

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.ListScheduleAdapter
import com.example.lmsstikes.adapter.ScheduleDetailAdapter
import com.example.lmsstikes.databinding.FragmentScheduleDetailBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Schedule
import com.example.lmsstikes.model.Session
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_schedule_detail.*
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class ScheduleDetailFragment: BaseFragment(){

    private lateinit var binding: FragmentScheduleDetailBinding
    private val viewModel by inject<MenuViewModel>()
    private var isShown = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule_detail, container, false)
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
                UtilityHelper.snackbarLong(view_parent, getString(com.example.lmsstikes.R.string.error_network))
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
                    viewModel.getListScheduleDetail(
                        AppPreference.getMonth(),
                        listSchedule[listSchedule.lastIndex].name.substring(0, 4).toInt()
                    )
                }
            })
            listSession.observe(viewLifecycleOwner, Observer {
                setListSession(it)
            })
            clickPeriod.observe(viewLifecycleOwner, Observer {
                viewModel.getListSchedule()
            })
        }
        setView()
    }

    private fun setView(){
        setToolbar(getString(R.string.detail_schedule))
        setNavigation()
        viewModel.getListSchedule()
        viewModel.getListScheduleDetail(
            AppPreference.getMonth(),
            AppPreference.getYear()
        )
    }

    private fun setListSession(list: ArrayList<Session>) {
        rv_schedule_detail.layoutManager = LinearLayoutManager(context)
        rv_schedule_detail.adapter = activity?.let {
            ScheduleDetailAdapter(it, list)
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
            viewModel.getListScheduleDetail(
                AppPreference.getMonth(),
                list[position].name.substring(0, 4).toInt()
            )
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
        fun newInstance() = ScheduleDetailFragment()
    }

}