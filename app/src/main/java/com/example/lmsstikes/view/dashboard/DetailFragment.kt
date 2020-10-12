package com.example.lmsstikes.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.lmsstikes.R
import com.example.lmsstikes.databinding.FragmentDetailBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject

class DetailFragment : BaseFragment(){

    private lateinit var binding: FragmentDetailBinding
    private val viewModel by inject<DashboardViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
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
        }
        setView()
    }

    private fun setView(){
        if (arguments?.getString(ARG_TITLE) == "About Apps"){
            title.visibility = View.GONE
            date.visibility = View.GONE
            content.visibility = View.GONE
            about.visibility = View.VISIBLE
        }
        else {
            viewModel.title.value = arguments?.getString(ARG_TITLE)
            viewModel.date.value = UtilityHelper.getSdfDayMonthYear(arguments?.getString(ARG_DATE))
            viewModel.content.value = arguments?.getString(ARG_CONTENT)
            viewModel.image.value = arguments?.getString(ARG_IMG)
            context?.let { UtilityHelper.setImage(it, viewModel.image.value.toString(), image) }
        }

        setToolbar(getString(R.string.detail))
        setNavigation()
    }

    companion object {
        const val ARG_IMG = "image"
        const val ARG_TITLE = "title"
        const val ARG_DATE = "date"
        const val ARG_CONTENT = "content"


        fun newInstance(img: String, title: String, date: String, content: String): DetailFragment {
            val fragment = DetailFragment()

            val bundle = Bundle().apply {
                putString(ARG_IMG, img)
                putString(ARG_TITLE, title)
                putString(ARG_DATE, date)
                putString(ARG_CONTENT, content)
            }

            fragment.arguments = bundle

            return fragment
        }
    }
}