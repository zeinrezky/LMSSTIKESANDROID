package com.example.lmsstikes.view.dashboard

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.lmsstikes.R
import com.example.lmsstikes.databinding.FragmentDetailBinding
import com.example.lmsstikes.helper.UtilityHelper
import com.example.lmsstikes.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject
import java.io.FileNotFoundException
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class DetailFragment : BaseFragment(), Html.ImageGetter{

    private lateinit var binding: FragmentDetailBinding
    private val viewModel by inject<DashboardViewModel>()
    private var empty: Drawable? = null

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
            viewModel.image.value = arguments?.getString(ARG_IMG)
            context?.let { UtilityHelper.setImage(it, viewModel.image.value.toString(), image) }

            val spanned: Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(arguments?.getString(ARG_CONTENT), Html.FROM_HTML_MODE_LEGACY, this, null)
            } else {
                Html.fromHtml(arguments?.getString(ARG_CONTENT),this, null)
            }
            content.text = spanned
        }

        setToolbar(getString(R.string.detail))
        setNavigation()
    }

    override fun getDrawable(s: String?): Drawable? {
        val d = LevelListDrawable()
        d.addLevel(0, 0, empty)
        empty?.let { d.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight) }
        LoadImage().execute(s, d)
        return d
    }

    inner class LoadImage : AsyncTask<Any?, Void?, Bitmap?>() {
        private var mDrawable: LevelListDrawable? = null

        override fun doInBackground(vararg params: Any?): Bitmap?{
            val source = params[0] as String
            mDrawable = params[1] as LevelListDrawable
            Log.d(ContentValues.TAG, "doInBackground $source")
            try {
                val inputStream = URL(source).openStream()
                return BitmapFactory.decodeStream(inputStream)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            if (bitmap != null) {
                val d = BitmapDrawable(bitmap)
                mDrawable!!.addLevel(1, 1, d)
                mDrawable!!.setBounds(0, 0, bitmap.width, bitmap.height)
                mDrawable!!.level = 1
                val t: CharSequence = content?.text!!
                content?.text = t
            }
        }
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