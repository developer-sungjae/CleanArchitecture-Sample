package com.sungjae.portfolio.content.di

import com.sungjae.portfolio.content.ContentDataSourceImpl
import com.sungjae.portfolio.data.ContentDataSource
import org.koin.dsl.module

val contentDataSourceModule = module {

    single<ContentDataSource> { ContentDataSourceImpl(get()) }
}