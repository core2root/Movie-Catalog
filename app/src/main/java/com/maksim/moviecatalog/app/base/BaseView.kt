package com.maksim.moviecatalog.app.base

import com.maksim.moviecatalog.app.di.AppContainer

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
interface BaseView {

  fun showMessage(message: String)
  fun showMessage(resId: Int)

  fun showLoading()
  fun hideLoading()

  fun getAppContainer(): AppContainer

}