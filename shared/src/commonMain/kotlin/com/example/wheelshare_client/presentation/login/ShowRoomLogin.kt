package com.example.wheelshare_client.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.wheelshare_client.presentation.components.PrimarySimpleButton
import com.example.wheelshare_client.presentation.components.platform.IconifiedTextInputField
import com.example.wheelshare_client.presentation.theme.LocalWheelShareColors
import com.example.wheelshare_client.presentation.theme.LocalWheelShareSpacing
import com.example.wheelshare_client.util.DrawableHelper
import com.example.wheelshare_client.util.Strings
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class ShowRoomLoginScreen: Screen {
    @Composable
    override fun Content() {
        ScreenContent()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ScreenContent() {
    Box(
        modifier = Modifier.fillMaxSize().background(LocalWheelShareColors.current.background)
    ) {
        Image(
            painter = painterResource(res = DrawableHelper.WHEEL_SHARE_TITLE),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.align(Alignment.TopStart)
                .padding(LocalWheelShareSpacing.current.l)
                .size(180.dp, 56.dp)
        )
        Text(
            text = Strings.SHOWROOM_GREET,
            style = MaterialTheme.typography.h3.copy(
                color = LocalWheelShareColors.current.primary,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 160.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalWheelShareSpacing.current.l),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
                .padding(horizontal = LocalWheelShareSpacing.current.l)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconifiedTextInputField(
                    label = Strings.USER_NAME,
                    placeHolder = Strings.USER_NAME
                )
                IconifiedTextInputField(
                    label = Strings.PASSWORD,
                    placeHolder = Strings.PASSWORD
                )
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(bottom = 200.dp)
                .padding(horizontal = LocalWheelShareSpacing.current.l),
            verticalArrangement = Arrangement.spacedBy(LocalWheelShareSpacing.current.s),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimarySimpleButton(
                text = Strings.LOGIN,
                onClick = {

                }
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(LocalWheelShareSpacing.current.xs)
            ) {
                Text(
                    text = Strings.NO_ACCOUNT,
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = LocalWheelShareColors.current.onBackground
                    ),
                )
                Text(
                    text = Strings.REGISTER,
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = LocalWheelShareColors.current.primary,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.clickable {

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