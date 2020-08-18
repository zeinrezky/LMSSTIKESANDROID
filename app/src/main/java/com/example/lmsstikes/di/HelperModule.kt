package com.example.lmsstikes.di

import com.example.lmsstikes.helper.PermissionHelper
import com.example.lmsstikes.helper.UtilityHelper
import org.koin.dsl.module

val HelperModule = module {
    single { PermissionHelper() }
    single { UtilityHelper() }

}