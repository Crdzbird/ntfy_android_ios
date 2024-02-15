package ints.devotion.myapplication.ui.map

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import ints.devotion.myapplication.R
import ints.devotion.myapplication.model.IssNow
import ints.devotion.myapplication.model.ViewState
import ints.devotion.myapplication.ui.map.viewModel.MapViewModel

@Composable
fun MapScreen(mapViewModel: MapViewModel = hiltViewModel()) {
    var issPosition by remember { mutableStateOf(LatLng(0.0, 0.0)) }

    when(val mapState = mapViewModel.mapState.collectAsState().value) {
        is ViewState.Success -> {
            val issNow = mapState.success as IssNow
            issPosition = LatLng(issNow.issPosition.latitude.toDouble(), issNow.issPosition.longitude.toDouble())
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(issPosition, 10f)
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = issPosition),
                    title = stringResource(id = R.string.iss_location),
                    snippet = stringResource(id = R.string.iss_location)
                )
            }
        }
        else -> Loader()
    }

}

@Composable
private fun Loader() = CircularProgressIndicator()

