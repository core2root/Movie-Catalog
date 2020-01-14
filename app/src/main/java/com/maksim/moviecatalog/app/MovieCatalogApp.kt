package com.maksim.moviecatalog.app

import android.app.Application
import com.maksim.moviecatalog.app.di.AppContainer

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class MovieCatalogApp: Application() {

  private lateinit var appContainer: AppContainer

  override fun onCreate() {
    super.onCreate()

    appContainer = AppContainer(applicationContext)

  }

  fun getAppContainer(): AppContainer{
    return appContainer
  }

}