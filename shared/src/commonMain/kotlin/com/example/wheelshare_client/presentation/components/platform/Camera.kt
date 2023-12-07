package com.example.wheelshare_client.presentation.components.platform

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator

sealed class ImageDataResult {
    object Idle : ImageDataResult()
    object Loading : ImageDataResult()
    data class Success(val fileName: String, val byteArray: ByteArray) :
        ImageDataResult()
    object Error : ImageDataResult()
}

@Composable
expect fun CameraCompose(
    isCameraButton: Boolean,
    context: Any,
    destinationsNavigator: Navigator,
    cameraResult: (ImageDataResult) -> Unit,
)
