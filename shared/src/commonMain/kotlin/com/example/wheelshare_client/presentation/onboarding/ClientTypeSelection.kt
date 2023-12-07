package com.example.wheelshare_client.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.wheelshare_client.presentation.components.PrimarySimpleButton
import com.example.wheelshare_client.presentation.login.RenterLoginScreen
import com.example.wheelshare_client.presentation.login.ShowRoomLoginScreen
import com.example.wheelshare_client.presentation.login.VehicleOwnerLoginScreen
import com.example.wheelshare_client.presentation.theme.LocalWheelShareColors
import com.example.wheelshare_client.presentation.theme.LocalWheelShareSpacing
import com.example.wheelshare_client.util.DrawableHelper
import com.example.wheelshare_client.util.Strings
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class ClientTypeSelectionScreen : Screen {
    @Composable
    override fun Content() {
        ScreenContent()
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ScreenContent(
    navigator: Navigator = LocalNavigator.currentOrThrow
) {
    Box(
        modifier = Modifier.fillMaxSize().background(LocalWheelShareColors.current.background)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalWheelShareSpacing.current.s),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 100.dp)
        ) {
            Image(
                painter = painterResource(res = DrawableHelper.WHEEL_SHARE_TITLE),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(180.dp, 56.dp)
            )
            Text(
                text = Strings.APP_DESCRIPTION,
                style = MaterialTheme.typography.body1.copy(
                    color = LocalWheelShareColors.current.onBackground
                )
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalWheelShareSpacing.current.l),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center).padding(horizontal = LocalWheelShareSpacing.current.l)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrimarySimpleButton(
                    text = Strings.BECOME_A_RENTER,
                    onClick = {
                        navigator.push(RenterLoginScreen())
                    }
                )
                PrimarySimpleButton(
                    text = Strings.RENT_A_CAR,
                    onClick = {
                        navigator.push(VehicleOwnerLoginScreen())
                    }
                )
                PrimarySimpleButton(
                    text = Strings.REGISTER_SHOWROOM,
                    onClick = {
                        navigator.push(ShowRoomLoginScreen())
                    }
                )
            }

        }
        Image(
            painter = painterResource(res = DrawableHelper.YELLOW_CAR),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.align(Alignment.BottomEnd).size(240.dp, 198.dp)
        )
    }
}
