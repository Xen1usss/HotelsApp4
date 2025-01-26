package ks.hotelsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.InstallIn
import ks.hotelsapp.data.HotelsApi
import ks.hotelsapp.data.HotelsRepositoryImpl
import ks.hotelsapp.domain.HotelsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

// модуль для предоставления зависимостей Retrofit и API.

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/iMofas/ios-android-test/master/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHotelsApi(retrofit: Retrofit): HotelsApi {
        return retrofit.create(HotelsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHotelsRepository(hotelsRepositoryImpl: HotelsRepositoryImpl): HotelsRepository {
        return hotelsRepositoryImpl
    }
}
