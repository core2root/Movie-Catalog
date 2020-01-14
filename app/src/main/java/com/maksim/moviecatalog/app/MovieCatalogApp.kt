package com.maksim.moviecatalog.app

import android.app.Application
import com.maksim.moviecatalog.app.di.AppContainer
import com.maksim.moviecatalog.data.local.model.MyObjectBox
import io.objectbox.BoxStore

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class MovieCatalogApp : Application() {

  private lateinit var appContainer: AppContainer
  private var boxStore: BoxStore? = null

  override fun onCreate() {
    super.onCreate()

    if (boxStore == null) {
      boxStore = MyObjectBox.builder()
        .androidContext(applicationContext)
        .build()
    }

    appContainer = AppContainer(boxStore!!)

  }

  fun getAppContainer(): AppContainer {
    return appContainer
  }

}