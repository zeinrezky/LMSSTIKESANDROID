package com.example.lmsstikes.view.menu

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.SessionScheduleAdapter
import com.example.lmsstikes.databinding.FragmentScheduleBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Session
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view_parent
import kotlinx.android.synthetic.main.toolbar.*
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
            listSession.observe(viewLifecycleOwner, Observer {
                setListSession(it)
            })

            clickInfo.observe(viewLifecycleOwner, Observer {
                showDialog()
            })
        }
        if (arguments?.getBoolean(ARG_IS_TOOLBAR_VISIBLE)!!){
            toolbar.visibility = View.VISIBLE
        } else {
            toolbar.visibility = View.GONE
        }
        setView()
    }

    private fun setView(){
        setToolbar(getString(R.string.schedule))
        setNavigation()

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val currentDate: String = sdf.format(Date(year - 1900, month, day))
        viewModel.date.value = UtilityHelper.getSdfDMY(currentDate)
        viewModel.getListSessionSchedule(day, month + 1, year)

        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->

            val selectedDate: String = sdf.format(Date(year - 1900, month, dayOfMonth))
            viewModel.date.value = UtilityHelper.getSdfDMY(selectedDate)
            viewModel.getListSessionSchedule(dayOfMonth, month + 1, year)
        }
    }

    private fun setListSession(list: ArrayList<Session>) {

        rv_schedule.layoutManager = LinearLayoutManager(context)
        rv_schedule.adapter = activity?.let {
            SessionScheduleAdapter(it, list)
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
        const val ARG_IS_TOOLBAR_VISIBLE = "toolbar"


        fun newInstance(isVisible: Boolean): ScheduleFragment {
            val fragment = ScheduleFragment()

            val bundle = Bundle().apply {
                putBoolean(ARG_IS_TOOLBAR_VISIBLE, isVisible)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

}