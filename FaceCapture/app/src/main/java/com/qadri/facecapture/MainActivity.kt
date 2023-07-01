import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
class MainActivity : AppCompatActivity() {
    private val requiredPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    App()
                }
            }
        }
    }

    @Composable
    fun App() {
        val cameraPermissionsState = rememberMultiplePermissionsState(
            permissions = arrayOf(Manifest.permission.CAMERA).toList()
        )
        val locationPermissionState = rememberMultiplePermissionsState(
            permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION).toList()
        )
        val faceCount = remember { mutableStateOf(0) }

        when {
            cameraPermissionsState.allPermissionsGranted -> {
                CameraScreen(
                    onFaceDetected = { faceCount.value++ }
                )
            }
            cameraPermissionsState.shouldShowRationale ||
                    locationPermissionState.shouldShowRationale -> {
                PermissionRationaleScreen(
                    onPermissionRequested = {
                        cameraPermissionsState.launchMultiplePermissionRequest()
                        locationPermissionState.launchMultiplePermissionRequest()
                    }
                )
            }
            else -> {
                PermissionDeniedScreen()
            }
        }
    }

    @Composable
    fun CameraScreen(onFaceDetected: () -> Unit) {
        val context = LocalContext.current
        val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
        val cameraSelector = remember { CameraSelector.DEFAULT_FRONT_CAMERA }
        val imageAnalysis = remember {
            ImageAnalysis.Builder()
                .build()
                .also { it.setAnalyzer(lifecycleScope, FaceAnalyzer(onFaceDetected)) }
        }
        val imageCapture = remember {
            ImageCapture.Builder()
                .build()
        }

        val cameraPermissionsState = rememberMultiplePermissionsState(
            permissions = arrayOf(Manifest.permission.CAMERA).toList()
        )
        val locationPermissionState = rememberMultiplePermissionsState(
            permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION).toList()
        )

        CameraPreview(
            cameraProviderFuture = cameraProviderFuture,
            cameraSelector = cameraSelector,
            imageAnalysis = imageAnalysis,
            imageCapture = imageCapture,
            modifier = Modifier.fillMaxSize()
        )
    }

    @Composable
    fun CameraPreview(
        cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
        cameraSelector: CameraSelector,
        imageAnalysis: ImageAnalysis,
        imageCapture: ImageCapture,
        modifier: Modifier = Modifier
    ) {
        val context = LocalContext.current
        val previewView = remember {
            PreviewView(context).apply {
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }
        }

        LaunchedEffect(cameraProviderFuture) {
            val cameraProvider = cameraProviderFuture.await()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            try {
                val camera = cameraProvider.bindToLifecycle(
                    lifecycleOwner = this@MainActivity,
                    cameraSelector = cameraSelector,
                    preview = preview,
                    imageAnalysis = imageAnalysis,
                    imageCapture = imageCapture
                )
                preview.setTargetResolution(camera.cameraInfo.previewResolution)
            } catch (ex: Exception) {
                // Handle camera binding error
            }
        }

        AndroidView(
            factory = { previewView },
            modifier = modifier
        )
    }

    class FaceAnalyzer(private val onFaceDetected: () -> Unit) : ImageAnalysis.Analyzer {
        override fun analyze(image: ImageProxy) {
            // Face detection logic here
            // Increment face count using onFaceDetected() function
            // Call API endpoint to send the face count data

            image.close()
        }
    }

    @Composable
    fun PermissionRationaleScreen(onPermissionRequested: () -> Unit) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Please grant the required permissions to continue.")

            // Display rationale for each permission
            val cameraPermissionsState = rememberMultiplePermissionsState(
                permissions = arrayOf(Manifest.permission.CAMERA).toList()
            )
            val locationPermissionState = rememberMultiplePermissionsState(
                permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION).toList()
            )
            DisplayRationale(permissionState = cameraPermissionsState)
            DisplayRationale(permissionState = locationPermissionState)

            // Request permissions on button click
            Button(
                onClick = {
                    onPermissionRequested()
                }
            ) {
                Text(text = "Grant Permissions")
            }
        }
    }

    @Composable
    fun DisplayRationale(permissionState: MultiplePermissionsState) {
        val context = LocalContext.current

        when (permissionState) {
            is PermissionState.Denied.ShouldShowRationale -> {
                Text(text = "Permission: ${permissionState.permission}")
            }
            is PermissionState.Denied.ShouldNotShowRationale -> {
                Text(text = "Permission: ${permissionState.permission}")

                Button(
                    onClick = {
                        ActivityCompat.requestPermissions(
                            context as Activity,
                            arrayOf(permissionState.permission),
                            PERMISSION_REQUEST_CODE
                        )
                    }
                ) {
                    Text(text = "Request Permission")
                }
            }
        }
    }

    @Composable
    fun PermissionDeniedScreen() {
        Text(text = "Required permissions are denied.")
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                App()
            }
        }
    }
}


