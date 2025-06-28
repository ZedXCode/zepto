package com.zepto.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zepto.data.api.FakeStoreApiService
import com.zepto.data.repository.CartRepository
import com.zepto.data.repository.CartRepositoryImpl
import com.zepto.data.repository.CategoryRepository
import com.zepto.data.repository.CategoryRepositoryImpl
import com.zepto.data.repository.LocationRepository
import com.zepto.data.repository.LocationRepositoryImpl
import com.zepto.data.repository.ProductRepository
import com.zepto.data.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

private const val TAG = "Module"

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindingModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository

    @Binds
    @Singleton
    abstract fun bindLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository

    @Provides
    @Singleton
    fun provideFakeStoreApiService(): FakeStoreApiService {
        return FakeStoreApiService()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(TAG, "API Call: $message")
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}