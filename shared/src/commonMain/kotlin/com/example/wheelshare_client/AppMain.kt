package com.example.wheelshare_client

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.wheelshare_client.di.initKoin
import com.example.wheelshare_client.presentation.onboarding.ClientTypeSelectionScreen
import com.example.wheelshare_client.presentation.theme.WheelShareTheme
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

@Composable
fun AppMain(isDarkTheme: Boolean) {
    WheelShareTheme(darkTheme = isDarkTheme) {
        AppMainNavigator()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AppMainNavigator() {
    BottomSheetNavigator {
        if(Firebase.auth.currentUser == null)
            Navigator(ClientTypeSelectionScreen()) {
                SlideTransition(it)
            }
    }
}
