package com.dbernic.testtask.ui.mapScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dbernic.testtask.R


@Composable
fun Details () {

    val scrollState = rememberScrollState()
    val scrollState2 = rememberScrollState()

    Card (
        modifier = Modifier.fillMaxWidth().height(635.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column (
                modifier = Modifier
                    .padding(top = 23.dp, start = 19.dp, end = 24.dp)
                    .fillMaxSize()
            ) {
                Row {
                    Column (
                        modifier = Modifier.weight(1f)
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(
                                painter = painterResource(R.drawable.ic_reserved_yellow),
                                contentDescription = null,
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Reserve Parking at",
                                fontSize = 12.sp,
                                color = Color(0xFFF9BA15),
                                style = TextStyle(
                                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                                ),
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "David’s Spot",
                                fontSize = 12.sp,
                                color = Color(0xFFA5AAB7),
                                style = TextStyle(
                                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                                ),
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "On Spot Parking",
                            fontSize = 18.sp,
                            color = Color(0xFF606470),
                            style = TextStyle(
                                platformStyle = PlatformTextStyle(includeFontPadding = false),
                            ),
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Row (
                            modifier = Modifier.padding(start = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_car),
                                contentDescription = null,
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "2 Car Spots",
                                fontSize = 12.sp,
                                color = Color(0xFFA5AAB7),
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
                                text = "26 km away",
                                fontSize = 12.sp,
                                color = Color(0xFFA5AAB7),
                                style = TextStyle(
                                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                                ),
                            )
                            Spacer(Modifier.width(20.dp))

                        }
                    }

                    Column {
                        Text(
                            modifier = Modifier.padding(end = 7.dp),
                            text = "\u200E₦500",
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
                Spacer(Modifier.height(16.dp))
                HorizontalDivider(
                    color = Color(0xFFEFEFEF)
                )

                Row (
                    modifier = Modifier.padding(14.dp)
                ) {
                    Image(
                        modifier = Modifier.padding(start = 4.dp),
                        painter = painterResource(R.drawable.ic_call),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Call",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA5AAB7),
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )

                    Image(
                        modifier = Modifier.padding(start = 4.dp),
                        painter = painterResource(R.drawable.ic_directions),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Directions",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA5AAB7),
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )
                    Spacer(Modifier.width(24.dp))

                    Image(
                        modifier = Modifier.padding(start = 4.dp),
                        painter = painterResource(R.drawable.ic_upload),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Share",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA5AAB7),
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )
                }

                HorizontalDivider(
                    color = Color(0xFFEFEFEF)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Info",
                    fontSize = 18.sp,
                    color = Color(0xFF606470),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = "They oofer 24/7 access to our parking lot, we know you may need your vechile at any given point of time while offering a 25 % cashback on the base amount if the parking experience ....  Read More",
                    fontSize = 12.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFFA5AAB7),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )

                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Price Tariff",
                    fontSize = 18.sp,
                    color = Color(0xFF606470),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = "you can change the duration of booking by selecting any one of the options below",
                    fontSize = 12.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFFA5AAB7),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )

                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Weekdays",
                    fontSize = 12.sp,
                    color = Color(0xFF606470),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .horizontalScroll(scrollState)
                ) {
                    DayPrice(1,2,"$200", false)
                    Spacer(Modifier.width(11.dp))
                    DayPrice(3,4,"$200", true)
                    Spacer(Modifier.width(11.dp))
                    DayPrice(3,4,"$200", false)
                }

                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Weekends",
                    fontSize = 12.sp,
                    color = Color(0xFF606470),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .horizontalScroll(scrollState2)
                ) {
                    DayPrice(1,2,"$200", false)
                    Spacer(Modifier.width(11.dp))
                    DayPrice(3,4,"$200", false)
                    Spacer(Modifier.width(11.dp))
                    DayPrice(3,4,"$200", false)
                }
            }

            Row (
                modifier = Modifier.height(104.dp).fillMaxWidth()
                    .background(Color(0xFFFCFCFC)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(31.dp))
                Image(
                    painter = painterResource(R.drawable.ic_reserved),
                    contentDescription = null,
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "15 Spots ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF42C942),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )
                Text(
                    text = "Available",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFA5AAB7),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )
                Spacer(Modifier.weight(1f))
                Card (
                    modifier = Modifier.height(54.dp).width(159.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF3277D8),
                    ),
                ) {
                    Column (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            Text(
                                text = "Reserve for ",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFFBFBFB),
                                style = TextStyle(
                                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                                ),
                            )

                            Text(
                                text = "$900",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFFBFBFB),
                                style = TextStyle(
                                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                                ),
                            )
                        }

                        Text(
                            text = "3hr - 4hrs:",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xB3FBFBFB),
                            style = TextStyle(
                                platformStyle = PlatformTextStyle(includeFontPadding = false),
                            ),
                        )
                    }
                }
                Spacer(Modifier.width(31.dp))

            }

        }

    }
}

@Composable
fun DayPrice(
    startHr: Int,
    endHr: Int,
    price: String,
    isActive: Boolean
) {

    val color = if (isActive) Color(0xFF3277D8) else Color(0xFFA5AAB7)

    OutlinedCard(
        modifier = Modifier.width(143.dp).height(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, color),
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(Modifier.width(10.dp))
            Text(
                text = "${startHr}hr - ${endHr}hrs:",
                fontSize = 12.sp,
                color = color,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                ),
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = price,
                fontSize = 20.sp,
                color = color,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                ),
            )
        }
    }

}