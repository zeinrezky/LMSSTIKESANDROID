package com.example.lmsstikes.di

import com.example.lmsstikes.repository.UserRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { UserRepository(get()) }
}