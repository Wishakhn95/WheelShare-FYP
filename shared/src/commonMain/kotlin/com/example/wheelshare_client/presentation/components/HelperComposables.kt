package com.example.wheelshare_client.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun setNullableIcon(icon: String?, contentDescription: String? = null): @Composable (() -> Unit)? {
    return if (icon != null) {
        {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(icon),
                contentDescription = contentDescription,
                tint = MaterialTheme.colors.onSurface
            )
        }
    } else {
        null
    }
}