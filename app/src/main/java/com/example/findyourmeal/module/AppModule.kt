package com.example.findyourmeal.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.findyourmeal.data.api.ApiConstants
import com.example.findyourmeal.data.api.ApiService
import com.example.findyourmeal.room.DbCreation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context=context))
            .addNetworkInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder().build()
                chain.proceed(request)
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDbCreate(@ApplicationContext context: Context)=Room.databaseBuilder(
        context,DbCreation::class.java,"Saved_DataBase"
    ).build()

    @Provides
    @Singleton
    fun provideDao(myDb:DbCreation)=myDb.getYourDao()
}