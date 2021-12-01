package com.example.lmsstikes.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.lmsstikes.R
import com.example.lmsstikes.adapter.*
import com.example.lmsstikes.databinding.FragmentKnowledgeBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.model.Dashboard
import com.example.lmsstikes.util.Constant
import com.example.lmsstikes.view.base.BaseFragment
import com.example.lmsstikes.view.main.WebViewActivity
import kotlinx.android.synthetic.main.fragment_knowledge.*
import org.koin.android.ext.android.inject

class KnowledgeFragment : BaseFragment(), KnowledgeAdapter.Listener{

    private lateinit var binding: FragmentKnowledgeBinding
    private val viewModel by inject<DashboardViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_knowledge, container, false)
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
            listKnowledge.observe(viewLifecycleOwner, Observer {
                setListKnowledge(it)
            })
        }
        setView()
    }

    private fun setView(){
        viewModel.getListKnowledge()
        setToolbar(getString(R.string.knowledge))
        setNavigation()
    }

    private fun setListKnowledge(list: ArrayList<Dashboard.Knowledge>) {
        rv_knowledge.layoutManager = LinearLayoutManager(context)
        rv_knowledge.adapter = activity?.let {
            KnowledgeAdapter(it, list, this)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = KnowledgeFragment()
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
}