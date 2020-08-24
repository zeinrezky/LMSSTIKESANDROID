package com.example.lmsstikes.view.schedule

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.ListScheduleAdapter
import com.example.lmsstikes.databinding.FragmentScheduleBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Schedule
import com.example.lmsstikes.view.base.BaseFragment
import com.example.lmsstikes.view.dashboard.WhatsOnFragment
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.koin.android.ext.android.inject

class ScheduleFragment: BaseFragment(){

    private lateinit var binding: FragmentScheduleBinding
    private val viewModel by inject<ScheduleViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
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
                com.example.lmsstikes.helper.UtilityHelper.snackbarLong(view_parent, getString(com.example.lmsstikes.R.string.error_network))
            })
            isLoading.observe(viewLifecycleOwner, Observer { bool ->
                bool.let { loading ->
                    if(loading){ showWaitingDialog() }
                    else { hideWaitingDialog() }
                }
            })
            listSchedule.observe(viewLifecycleOwner, Observer {
//                showDialog(it)
            })
        }
        setView()
    }

    private fun setView(){
        viewModel.getListSchedule()
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
        fun newInstance() = ScheduleFragment()
    }

}