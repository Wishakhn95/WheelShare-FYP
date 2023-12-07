package com.example.wheelshare_client.presentation.components.platform

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.wheelshare_client.presentation.theme.LocalWheelShareColors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import kotlin.random.Random

@Composable
actual fun CameraCompose(
    isCameraButton: Boolean,
    context: Any,
    destinationsNavigator: Navigator,
    cameraResult: (ImageDataResult) -> Unit,
) {

    val owner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraX2 = CameraHelper(context = context as Context, owner = owner)

    var hasCamPermission by remember {
        mutableStateOf(
            REQUIRED_PERMISSIONS.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            })
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { granted -> hasCamPermission = granted.size == 2 })
    LaunchedEffect(key1 = true) {
        launcher.launch(
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {

        if (hasCamPermission) {
            AndroidView(
                modifier = Modifier.fillMaxSize(), factory = { cameraX2.startCameraPreviewView() })
        }
    }
    Row(
        modifier = Modifier.fillMaxSize().padding(bottom = 24.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        CaptureButton(isCameraButton = isCameraButton) {
            cameraX2.capturePhoto(
                id = Random.nextInt(0, 1000), count = 1, cameraResult = cameraResult
            )
        }
    }
}

class CameraHelper(private var context: Any, var owner: LifecycleOwner) {
    private var imageCapture: ImageCapture? = null

    fun startCameraPreviewView(): PreviewView {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(context as Context)
        val previewView = PreviewView(context as Context)

        val preview =
            Preview.Builder().build().also { it.setSurfaceProvider(previewView.surfaceProvider) }

        imageCapture =
            ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()

        val camSelector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

        try {

            cameraProviderFuture.get().bindToLifecycle(owner, camSelector, preview, imageCapture)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return previewView
    }

    fun capturePhoto(id: Int, count: Int, cameraResult: (ImageDataResult) -> Unit) =
        owner.lifecycleScope.launch {
            val imageCapture = imageCapture ?: return@launch

            imageCapture.takePicture(
                ContextCompat.getMainExecutor(context as Context),
                object : ImageCapture.OnImageCapturedCallback(), ImageCapture.OnImageSavedCallback {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        super.onCaptureSuccess(image)
                        cameraResult(ImageDataResult.Loading)
                        owner.lifecycleScope
                            .launch {
                                saveMediaToStorage(
                                    image = image,
                                    "${Random.nextInt()}-$id-$count",
                                    cameraResult = cameraResult
                                )
                            }
                            .invokeOnCompletion { image.close() }
                    }

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {}

                    override fun onError(exception: ImageCaptureException) {
                        super.onError(exception)
                        cameraResult(ImageDataResult.Error)
                    }
                })
        }

    private suspend fun saveMediaToStorage(
        image: ImageProxy,
        name: String,
        cameraResult: (ImageDataResult.Success) -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            val planeProxy = image.planes[0]
            val buffer: ByteBuffer = planeProxy.buffer
            val bytes = ByteArray(buffer.remaining())
            buffer.get(bytes)
            val filename = "$name.png"
            cameraResult(ImageDataResult.Success(filename, bytes))
        }
    }

    //for image size reduction
    private suspend fun saveResizedMediaToStorage(
        image: ImageProxy,
        name: String,
        maxWidth: Int, // Set your desired maximum width
        maxHeight: Int, // Set your desired maximum height
        quality: Int, // Set your desired compression quality (0-100)
        cameraResult: (ImageDataResult.Success) -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            val planeProxy = image.planes[0]
            val buffer: ByteBuffer = planeProxy.buffer
            val bytes = ByteArray(buffer.remaining())
            buffer.get(bytes)

            // Decode the image
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)

            // Calculate the inSampleSize to resize the image
            options.inJustDecodeBounds = false
            options.inSampleSize = calculateInSampleSize(
                options,
                maxWidth,
                maxHeight
            ) // Implement this method to calculate the sample size

            // Decode the resized bitmap
            val resizedBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)

            // Convert the resized bitmap to a byte array
            val outputStream = ByteArrayOutputStream()
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            val resizedBytes = outputStream.toByteArray()

            val filename = "$name.png"
            cameraResult(ImageDataResult.Success(filename, resizedBytes))
        }
    }

    // Calculate the inSampleSize to resize the image based on the desired dimensions
    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }
}

val REQUIRED_PERMISSIONS =
    mutableListOf(
        Manifest.permission.CAMERA,
    )
        .apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
        .toTypedArray()

fun allPermissionsGranted(context: Context) =
    REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

@Composable
private fun CaptureButton(isCameraButton: Boolean, onCaptureClick: () -> Unit) {
    Card(
        shape = CircleShape,
        modifier = Modifier.size(80.dp).clickable { if (isCameraButton) onCaptureClick() },
        backgroundColor = Color.White
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier.size(60.dp).padding(4.dp),
            backgroundColor = if (isCameraButton) LocalWheelShareColors.current.error else LocalWheelShareColors.current.greyDark
        ) {}
    }
}