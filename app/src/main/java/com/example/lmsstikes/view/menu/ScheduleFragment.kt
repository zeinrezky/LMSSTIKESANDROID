package com.example.lmsstikes.view.menu

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.ListScheduleAdapter
import com.example.lmsstikes.databinding.FragmentScheduleBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Schedule
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class ScheduleFragment: BaseFragment(){

    private lateinit var binding: FragmentScheduleBinding
    private val viewModel by inject<MenuViewModel>()

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
                UtilityHelper.snackbarLong(view_parent, getString(com.example.lmsstikes.R.string.error_network))
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
            clickInfo.observe(viewLifecycleOwner, Observer {
                showDialog()
            })
        }
        setView()
    }

    private fun setView(){
//        viewModel.getListSchedule()
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val selectedDate: String = sdf.format(Date(year - 1900, month, dayOfMonth))
            viewModel.date.value = UtilityHelper.getSdfDMY(selectedDate)
        }
    }

    private fun showDialog() {
        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.dialog_schedule_indicator)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog?.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER

        dialog?.window?.attributes = lp
        dialog?.show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScheduleFragment()
    }

}