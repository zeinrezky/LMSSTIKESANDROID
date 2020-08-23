package com.example.lmsstikes.util.manager

import com.example.lmsstikes.view.base.BaseActivity
import com.example.lmsstikes.view.base.BaseFragment


/**
 * Created by 21 on 29/5/17.
 */

interface Navigator {

    fun <T : BaseFragment> load(tClass: Class<T>): FragmentActionPerformer<T>

    fun loadActivity(aClass: Class<out BaseActivity>): ActivityBuilder

    fun <T : BaseFragment> loadActivity(aClass: Class<out BaseActivity>, pageTClass: Class<T>): ActivityBuilder

    fun goBack()

    fun finish()

    fun toggleLoader(show: Boolean)

}