package com.mr.levelist.di

import com.mr.levelist.navigation.NavigationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::NavigationViewModel)
}