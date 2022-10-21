package dev.dbuzin.android.beerreview.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BeerReviewsApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}