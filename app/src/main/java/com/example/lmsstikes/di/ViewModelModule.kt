package com.example.lmsstikes.di

import com.example.lmsstikes.view.dashboard.DashboardViewModel
import com.example.lmsstikes.view.login.LoginViewModel
import com.example.lmsstikes.view.schedule.ScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ScheduleViewModel(get()) }

}