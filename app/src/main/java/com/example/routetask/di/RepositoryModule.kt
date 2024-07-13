package com.example.routetask.di

import com.example.routetask.model.repo.ProductsRepository
import com.example.routetask.model.repo.ProductsRepositoryImpl
import com.example.routetask.network.ProductRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideProductRepository(productRemoteDataSource: ProductRemoteDataSource): ProductsRepository =
        ProductsRepositoryImpl(productRemoteDataSource)


}