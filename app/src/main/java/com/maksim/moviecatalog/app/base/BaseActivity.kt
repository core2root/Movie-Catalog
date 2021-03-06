package com.maksim.moviecatalog.app.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maksim.moviecatalog.app.MovieCatalogApp
import com.maksim.moviecatalog.app.di.AppContainer

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
abstract class BaseActivity: AppCompatActivity(), BaseView {

  override fun showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

  override fun showMessage(resId: Int) {
    showMessage(getString(resId))
  }

  override fun showLoading() {
    //No base implementation, each view can decide to implement or not.
  }

  override fun hideLoading() {
    //No base implementation, each view can decide to implement or not.
  }

  override fun getAppContainer(): AppContainer{
    return (application as MovieCatalogApp).getAppContainer()
  }

}