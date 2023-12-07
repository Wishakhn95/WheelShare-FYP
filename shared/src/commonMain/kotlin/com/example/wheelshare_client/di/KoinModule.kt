package com.example.wheelshare_client.di

import com.example.wheelshare_client.presentation.login.viewmodel.LoginViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() = startKoin {
    modules( module {
        factory {
            LoginViewModel()
        }
    })
}