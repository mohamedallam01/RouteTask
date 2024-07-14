package com.example.routetask.di

import com.example.routetask.model.repo.ProductsRepository
import com.example.routetask.model.repo.ProductsRepositoryImpl
import com.example.routetask.network.ProductRemoteDataSource
import com.example.routetask.network.ProductRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {


    @Binds
    abstract fun provideRemoteDataSource(productRemoteDataSourceImpl: ProductRemoteDataSourceImpl) : ProductRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideProductRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository


}