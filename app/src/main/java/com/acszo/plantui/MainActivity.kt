package com.acszo.plantui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.acszo.plantui.ui.theme.PlantUiTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PlantUiTheme {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons,
                    )
                }

                val listCard: List<ItemCard> = listOf(
                    ItemCard(R.drawable.auto_awesome, "Most Popular", "This is a popular plant in our store"),
                    ItemCard(R.drawable.assignment, "Easy Care", "This plant is appropriate for beginners"),
                    ItemCard(R.drawable.park, "Faux Available", "Get the same look without the maintenance"),
                )

                val listColumn: List<ItemList> = listOf(
                    ItemList(R.drawable.water, "Water every Tuesday"),
                    ItemList(R.drawable.eco, "Feed once monthly")
                )

                val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "") },
                            scrollBehavior = scrollBehavior,
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        tint = MaterialTheme.colorScheme.onSurface,
                                        contentDescription = "Back",
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Filled.MoreVert,
                                        tint = MaterialTheme.colorScheme.onSurface,
                                        contentDescription = "More",
                                    )
                                }
                            }
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .verticalScroll(rememberScrollState()),
                    ) {
                        Text(
                            text = "Monstera\nUnique",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.displayLarge,
                        )
                        Image(
                            painterResource(
                                id = R.drawable.plant
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 25.dp, vertical = 10.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Plant",
                        )
                        LazyRow(
                            contentPadding = PaddingValues(18.dp, 5.dp, 18.dp, 5.dp)
                        ) {
                            items(items = listCard) { item ->
                                Card(
                                    modifier = Modifier
                                        .height(145.dp)
                                        .width(135.dp)
                                        .padding(8.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                    ),
                                ) {
                                    Column(
                                        modifier = Modifier.padding(10.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = item.icon),
                                            contentDescription = null,
                                        )
                                        Text(
                                            text = item.title,
                                            modifier = Modifier
                                                .padding(0.dp, 10.dp, 0.dp, 0.dp),
                                            style = MaterialTheme.typography.titleSmall,
                                        )
                                        Text(
                                            text = item.description,
                                            modifier = Modifier
                                                .padding(0.dp, 5.dp, 0.dp, 0.dp),
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                    }
                                }
                            }
                        }
                        Text(
                            text = "Care",
                            modifier = Modifier.padding(25.dp, 10.dp, 0.dp, 0.dp),
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        listColumn.forEach {
                            Row(
                                modifier = Modifier.padding(25.dp, 10.dp, 0.dp, 0.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = it.icon),
                                    modifier = Modifier.padding(25.dp, 0.dp, 25.dp, 0.dp),
                                    contentDescription = null,
                                )
                                Text(
                                    text = it.description,
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            }
                        }
                        Text(
                            text = "About",
                            modifier = Modifier.padding(25.dp, 20.dp, 0.dp, 0.dp),
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            text = "They are herbs or evergreen vines, growing to heights of 20 metres (66 ft) in trees, climbing by means of aerial roots which act as hooks over branches; these roots will also grow into the soil to help support the plant. Since the plant roots both into the soil and over trees, it is considered a hemiepiphyte.",
                            modifier = Modifier.padding(25.dp, 10.dp, 25.dp, 20.dp),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
    }
}