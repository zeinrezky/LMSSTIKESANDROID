package com.example.lmsstikes.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.*
import com.example.lmsstikes.databinding.FragmentWhatsOnBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Dashboard
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_whats_on.rv_whats_on
import kotlinx.android.synthetic.main.fragment_whats_on.view_parent
import org.koin.android.ext.android.inject

class WhatsOnFragment : BaseFragment(), WhatsOnAdapter.Listener{

    private lateinit var binding: FragmentWhatsOnBinding
    private val viewModel by inject<DashboardViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_whats_on, container, false)
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
            listWhatsOn.observe(viewLifecycleOwner, Observer {
                setListWhatsOn(it)
            })
        }
        setView()
    }

    private fun setView(){
        viewModel.getListWhatsOn()
        setToolbar(getString(R.string.whatson))
        setNavigation()

    }

    private fun setListWhatsOn(list: ArrayList<Dashboard.WhatsOn>) {
        rv_whats_on.layoutManager = LinearLayoutManager(context)
        rv_whats_on.adapter = activity?.let {
            WhatsOnAdapter(it, list.reversed() as ArrayList<Dashboard.WhatsOn>, this)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WhatsOnFragment()
    }

    override fun onItemClicked(data: Dashboard.WhatsOn) {
        addFragment(DetailFragment.newInstance(data.image, data.title, data.date, data.content))
    }
}