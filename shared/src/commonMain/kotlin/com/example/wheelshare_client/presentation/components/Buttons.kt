package com.example.wheelshare_client.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wheelshare_client.presentation.theme.LocalWheelShareColors
import com.example.wheelshare_client.presentation.theme.LocalWheelShareShapes

@Composable
fun PrimarySimpleButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    backGroundColor: Color = LocalWheelShareColors.current.primary,
    contentColor: Color = LocalWheelShareColors.current.onPrimary,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = LocalWheelShareShapes.current.buttonShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backGroundColor,
            contentColor =contentColor,
            disabledBackgroundColor = LocalWheelShareColors.current.greyLight,
            disabledContentColor = LocalWheelShareColors.current.greyDarkText
        ),
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.h4.copy(
                    color = contentColor
                )
            )
        }
    }

}