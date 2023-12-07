package com.example.wheelshare_client.presentation.components.platform

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator

@Composable
actual fun CameraCompose(
    isCameraButton: Boolean,
    context: Any,
    destinationsNavigator: Navigator,
    cameraResult: (ImageDataResult) -> Unit,
) {}