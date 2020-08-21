package com.example.lmsstikes.di

import com.example.lmsstikes.view.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { DashboardViewModel(get()) }
}