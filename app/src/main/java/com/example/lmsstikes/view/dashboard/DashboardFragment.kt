package com.example.lmsstikes.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.*
import com.example.lmsstikes.databinding.FragmentDashboardBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Dashboard
import com.example.lmsstikes.util.AppPreference
import com.example.lmsstikes.util.Constant
import com.example.lmsstikes.view.base.BaseFragment
import com.example.lmsstikes.view.login.LoginActivity
import com.example.lmsstikes.view.main.WebViewActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.android.ext.android.inject

class DashboardFragment : BaseFragment(), WhatsOnAdapter.Listener, KnowledgeAdapter.Listener, AboutAdapter.Listener, AnnouncementAdapter.Listener{

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel by inject<DashboardViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
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
            clickViewAllKnowledge.observe(viewLifecycleOwner, Observer {
                addFragment(KnowledgeFragment.newInstance())
            })
            clickViewAllWhatsOn.observe(viewLifecycleOwner, Observer {
                addFragment(WhatsOnFragment.newInstance())
            })
            clickLogout.observe(viewLifecycleOwner, Observer {
                backToLogin()
            })
            img.observe(viewLifecycleOwner, Observer {
                context?.let { it1 -> UtilityHelper.setImage(it1, it, profile_image) }
            })
        }
        setView()
    }


    private fun setView(){
        viewModel.getList()
        setToolbar(getString(R.string.dashboard))
        if (AppPreference.getLoginData().role == Constant.Role.DOSEN){
            view_gpa.visibility = View.GONE
            rv_announcement.visibility = View.GONE
        } else if (AppPreference.getLoginData().role == Constant.Role.MAHASISWA){
            view_gpa.visibility = View.VISIBLE
            rv_announcement.visibility = View.VISIBLE
        }
    }

    private fun setListAnnouncement(list: ArrayList<Dashboard.Announcement>) {
        rv_announcement.layoutManager = LinearLayoutManager(context)
        rv_announcement.adapter = activity?.let {
            AnnouncementAdapter(it, list, this)
        }
    }
    private fun setListKnowledge(list: ArrayList<Dashboard.Knowledge>) {
        rv_knowledge.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_knowledge.adapter = activity?.let {
            KnowledgeAdapter(it, list, this)
        }
    }
    private fun setListWhatsOn(list: ArrayList<Dashboard.WhatsOn>) {
        rv_whats_on.layoutManager = LinearLayoutManager(context)
        rv_whats_on.adapter = activity?.let {
            WhatsOnAdapter(it, list, this)
        }
    }
    private fun setListCampusDir(list: ArrayList<Dashboard.CampusDir>) {
        rv_campus_dir.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_campus_dir.adapter = activity?.let {
            CampusDirAdapter(it, list)
        }
    }
    private fun setListAbout(list: ArrayList<Dashboard.About>) {
        list.add(Dashboard.About(10, "", "About Apps", "Tentang Aplikasi", "ABOUT", "2020-10-02 00:00:00"))
        rv_about.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_about.adapter = activity?.let {
            AboutAdapter(it, list, this)
        }
    }

    private fun backToLogin() {
        AppPreference.deleteAll()
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }

    override fun onItemClicked(data: Dashboard.WhatsOn) {
        addFragment(DetailFragment.newInstance(data.image, data.title, data.date, data.content))
    }

    override fun onItemClicked(data: Dashboard.Knowledge) {
        if (data.link.isEmpty()){
            Toast.makeText(context, "Konten tidak tersedia", Toast.LENGTH_SHORT).show()
        }
        else {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(Constant.Header.URL, Constant.PDF_URL + data.link)
            context?.startActivity(intent)
        }
    }

    override fun onItemClicked(data: Dashboard.About) {
        addFragment(DetailFragment.newInstance(data.image, data.title, data.date, data.content))
    }

    override fun onItemClicked(data: Dashboard.Announcement) {
        addFragment(DetailFragment.newInstance(data.image, data.title, data.date, data.content))
    }
}