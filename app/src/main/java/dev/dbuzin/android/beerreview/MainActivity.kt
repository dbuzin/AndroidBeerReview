package dev.dbuzin.android.beerreview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.dbuzin.android.beerreview.navigation.AppNavigationController
import dev.dbuzin.android.design.theme.BeerReviewTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeerReviewTheme {
                val systemUiController = rememberSystemUiController()
                AppNavigationController(
                    systemUiController = systemUiController
                )
            }
        }
    }
}