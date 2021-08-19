package kg.bakai.repo.di

import kg.bakai.repo.data.network.ApiService
import kg.bakai.repo.data.repository.DataRepositoryImpl
import kg.bakai.repo.domain.repository.DataRepository
import kg.bakai.repo.ui.viewmodel.MainViewModel
import kg.bakai.repo.utils.UrlProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        clientBuilder
            .addInterceptor(loggingInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        clientBuilder.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(UrlProvider.baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repoModule = module {
    single<DataRepository> { DataRepositoryImpl(get()) }
}
