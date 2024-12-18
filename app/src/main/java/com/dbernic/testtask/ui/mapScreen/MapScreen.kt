package com.dbernic.testtask.ui.mapScreen

import android.Manifest
import android.annotation.SuppressLint
import androidx.annotation.RequiresPermission
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dbernic.testtask.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@SuppressLint("MissingPermission")
@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val permissions = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    PermissionBox(
        permissions = permissions,
        requiredPermissions = listOf(permissions.first()),
        onGranted = {
            CurrentLocationContent(
                usePreciseLocation = it.contains(Manifest.permission.ACCESS_FINE_LOCATION),
            )
        },
    )

    val alert = viewModel.alert.collectAsState().value

    if (alert.isNotEmpty()) {
        AlertDialog(
            title = { Text(text = "Result") },
            text = { Text(text = alert) },
            onDismissRequest = viewModel::hideAlert,
            confirmButton = {
                TextButton(onClick = viewModel::hideAlert) {
                    Text("Confirm")
                }
            },
        )
    }

    MapUI( viewModel::clickEvent )
}

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun CurrentLocationContent(usePreciseLocation: Boolean) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var locationInfo by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                // getting last known location is faster and minimizes battery usage
                // This information may be out of date.
                // Location may be null as previously no client has access location
                // or location turned of in device setting.
                // Please handle for null case as well as additional check can be added before using the method
                scope.launch(Dispatchers.IO) {
                    val result = locationClient.lastLocation.await()
                    locationInfo = if (result == null) {
                        "No last known location. Try fetching the current location first"
                    } else {
                        "Current location is \n" + "lat : ${result.latitude}\n" +
                                "long : ${result.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
                    }
                }
            },
        ) {
            Text("Get last known location")
        }

        Button(
            onClick = {
                //To get more accurate or fresher device location use this method
                scope.launch(Dispatchers.IO) {
                    val priority = if (usePreciseLocation) {
                        Priority.PRIORITY_HIGH_ACCURACY
                    } else {
                        Priority.PRIORITY_BALANCED_POWER_ACCURACY
                    }
                    val result = locationClient.getCurrentLocation(
                        priority,
                        CancellationTokenSource().token,
                    ).await()
                    result?.let { fetchedLocation ->
                        locationInfo =
                            "Current location is \n" + "lat : ${fetchedLocation.latitude}\n" +
                                    "long : ${fetchedLocation.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
                    }
                }
            },
        ) {
            Text(text = "Get current location")
        }
        Text(
            text = locationInfo,
        )
    }
}

@Composable
fun MapUI(
    onBtnClick: ()->Unit,
) {
    val scrollState = rememberScrollState()
    var isOpen by remember { mutableStateOf(false) }

    Column  {
        if (!isOpen) {
            MapToolbar()
        }

        Box (
            contentAlignment = Alignment.BottomEnd
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize()
            )

            Column {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    FloatingActionButton(
                        modifier = Modifier.padding(end=20.dp),
                        onClick = onBtnClick,
                        containerColor = Color.White
                    ) {
                        Image(
                            modifier = Modifier.width(20.dp).height(20.dp),
                            painter = painterResource(R.drawable.ic_nav),
                            contentDescription = ""
                        )
                    }
                }

                Spacer(Modifier.height(28.dp))

                Row(
                    modifier = Modifier
                        .horizontalScroll(scrollState)
                        .padding(start = 16.dp, bottom = 16.dp)
                        .clickable { isOpen = !isOpen }
                ) {
                    LocationItem(
                        owner = "David's Spot",
                        name = "On Spot Parking",
                        price = "$50",
                        spots = 15,
                        distance = "2 km"
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    LocationItem(
                        owner = "ICM",
                        name = "Parking Lot",
                        price = "$50",
                        spots = 213,
                        distance = "26 km"
                    )
                }
            }

            if (isOpen) { // TODO condition
                Details()
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapToolbar() {
    Row (
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
    ){
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = "Where to park?",//text showed on SearchBar
                    onQueryChange = {}, //update the value of searchText
                    onSearch = {}, //the callback to be invoked when the input service triggers the ImeAction.Search action
                    expanded = false, //whether the user is searching or not
                    onExpandedChange = {},
                    enabled = true,
                    leadingIcon = { Image(
                        Icons.Default.Search,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Gray),
                    ) }

                )
            },
            expanded = false,
            onExpandedChange = {},
            modifier = Modifier.weight(1f)
              .padding(end = 16.dp),
            content = {},
        )
        Image(
            modifier = Modifier
                .width(32.dp).height(80.dp)
                .padding(top = 48.dp),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Gray),
            imageVector = Icons.Default.Menu
        )
    }
}

@Composable
fun LocationItem(
    owner: String,
    name: String,
    price: String,
    spots: Int,
    distance: String
) {
    Card (
        modifier = Modifier.width(227.dp).height(102.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),

    ) {
        Column (
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp),
        ) {
            Row {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = owner,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        color = Color.DarkGray,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )
                }

                Column {
                    Text(
                        text = price,
                        fontSize = 16.sp,
                        color = Color(0xFF3277D8),
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.padding(1.dp),
                        text = "for hr",
                        fontSize = 7.sp,
                        color = Color.Gray,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )

                }

            }
            Spacer(Modifier.height(13.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_car),
                    contentDescription = null,
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "$spots Car Spots",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )
                Spacer(Modifier.width(20.dp))
                Image(
                    painter = painterResource(R.drawable.ic_pedestrian),
                    contentDescription = null,
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "$distance away",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )
                Spacer(Modifier.width(20.dp))
            }
        }
    }
}


@Composable
@Preview(device = Devices.PIXEL_5)
fun MapPreview() {
    MapUI{}
}