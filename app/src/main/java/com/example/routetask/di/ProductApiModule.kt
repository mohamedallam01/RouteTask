package com.example.routetask.di

import com.example.routetask.network.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class ProductApiModule {
    @InstallIn(SingletonComponent::class)
    @Module
    class ProductApiModule {
        @Singleton
        @Provides
        fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        @Singleton
        @Provides
        fun provideProductService(retrofit: Retrofit): ProductService =
            retrofit.create(ProductService::class.java)


    }
}