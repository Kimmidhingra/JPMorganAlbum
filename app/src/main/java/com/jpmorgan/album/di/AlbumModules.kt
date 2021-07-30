package com.jpmorgan.album.di

import android.app.Application
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jpmorgan.album.BuildConfig
import com.jpmorgan.album.database.AlbumDatabase
import com.jpmorgan.album.network.AlbumApi
import com.jpmorgan.album.network.NetworkConnectionInterceptor
import com.jpmorgan.album.utils.DatabaseConstants.DATABASE_NAME
import com.jpmorgan.album.utils.NetworkConstants.CONNECTION_TIME_OUT_IN_SECS
import com.jpmorgan.album.utils.NetworkConstants.READ_TIME_OUT_IN_SECS
import com.jpmorgan.album.utils.NetworkConstants.WRITE_TIME_OUT_IN_SECS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AlbumModules {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): AlbumApi =
        retrofit.create(AlbumApi::class.java)

    @Provides
    @Singleton
    fun provideOKHttpClient(app: Application): OkHttpClient =
        OkHttpClient.Builder().apply {
            readTimeout(READ_TIME_OUT_IN_SECS, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT_IN_SECS, TimeUnit.SECONDS)
            connectTimeout(CONNECTION_TIME_OUT_IN_SECS, TimeUnit.SECONDS)
            if (BuildConfig.LOGGIN_ENABLED) {
                // logging interceptor
                HttpLoggingInterceptor {
                    Timber.tag("Retrofit").i(it)
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }.let {
                    addInterceptor(it)
                }
                // chucker interceptor
                addInterceptor(ChuckerInterceptor(app))
                addInterceptor(NetworkConnectionInterceptor(app.applicationContext))
            }
        }.build()

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AlbumDatabase =
        Room.databaseBuilder(app, AlbumDatabase::class.java, DATABASE_NAME)
            .build()
}


//val albumModule = module {
//
//
////    factory {
////        AlbumViewModel(get())
////    }
//
//    single {
//        Room.databaseBuilder(androidApplication(), AlbumDatabase::class.java, DATABASE_NAME).build()
//    }
//    single {
//        get<AlbumDatabase>().albumDao()
//    }
//    single(named("serverApi")) {
//        (get(named("retrofitInitialization")) as Retrofit.Builder).build()
//            .create(ServerApi::class.java)
//
//    }
//    single(named("retrofitInitialization")) {
//        Retrofit.Builder().apply {
//            addConverterFactory(GsonConverterFactory.create())
//            client(get(named("okHTTPClient")))
//        }
//    }
//
//    single(named("okHTTPClient")) {
//        OkHttpClient.Builder().apply {
//            readTimeout(READ_TIME_OUT_IN_SECS, TimeUnit.SECONDS)
//            writeTimeout(WRITE_TIME_OUT_IN_SECS, TimeUnit.SECONDS)
//            connectTimeout(CONNECTION_TIME_OUT_IN_SECS, TimeUnit.SECONDS)
//            if (BuildConfig.LOGGIN_ENABLED) {
//                // logging interceptor
//                HttpLoggingInterceptor {
//                    Timber.tag("Retrofit").d(it)
//                }.apply {
//                    level = HttpLoggingInterceptor.Level.BODY
//                }.let {
//                    addInterceptor(it)
//                }
//                // chucker interceptor
//                addInterceptor(ChuckerInterceptor(androidApplication()))
//            }
//        }.build()
//    }
//
//
//    single<HelloRepository> { HelloRepositoryImpl() }
//    factory<AlbumRepository> { AlbumRepositoryImpl(get()) }
//
//    // MyViewModel ViewModel
//    viewModel { AlbumViewModel(get()) }
//}


//fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//    return Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL).client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create()).build()
//}
//
//fun provideOKHttpClient():OkHttpClient{
//
//}