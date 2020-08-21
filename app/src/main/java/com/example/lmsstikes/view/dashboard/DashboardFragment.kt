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
import com.example.lmsstikes.databinding.FragmentHomeBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Dashboard
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class DashboardFragment : BaseFragment(){

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by inject<DashboardViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
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
            listAnnouncement.observe(viewLifecycleOwner, Observer {
                setListAnnouncement(it)
            })
            listKnowledge.observe(viewLifecycleOwner, Observer {
                setListKnowledge(it)
            })
            listWhatsOn.observe(viewLifecycleOwner, Observer {
                setListWhatsOn(it)
            })
            listCampusDir.observe(viewLifecycleOwner, Observer {
                setListCampusDir(it)
            })
            listAbout.observe(viewLifecycleOwner, Observer {
                setListAbout(it)
            })
        }
        setView()
    }

    private fun setView(){
        viewModel.getList()
    }

    private fun setListAnnouncement(list: ArrayList<Dashboard.Announcement>) {
        rv_announcement.layoutManager = LinearLayoutManager(context)
        rv_announcement.adapter = activity?.let {
            AnnouncementAdapter(it, list)
        }
    }
    private fun setListKnowledge(list: ArrayList<Dashboard.Knowledge>) {
        rv_knowledge.layoutManager = LinearLayoutManager(context)
        rv_knowledge.adapter = activity?.let {
            KnowledgeAdapter(it, list)
        }
    }
    private fun setListWhatsOn(list: ArrayList<Dashboard.WhatsOn>) {
        rv_knowledge.layoutManager = LinearLayoutManager(context)
        rv_knowledge.adapter = activity?.let {
            WhatsOnAdapter(it, list)
        }
    }
    private fun setListCampusDir(list: ArrayList<Dashboard.CampusDir>) {
        rv_campus_dir.layoutManager = LinearLayoutManager(context)
        rv_campus_dir.adapter = activity?.let {
            CampusDirAdapter(it, list)
        }
    }
    private fun setListAbout(list: ArrayList<Dashboard.About>) {
        rv_about.layoutManager = LinearLayoutManager(context)
        rv_about.adapter = activity?.let {
            AboutAdapter(it, list)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }
}