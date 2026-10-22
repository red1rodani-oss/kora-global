package com.koraglobal.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koraglobal.app.data.repository.FakeFootballDataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotolaDetailScreen(
    competitionId: String,
    onBack: () -> Unit,
    onMatchClick: (String) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val isBotola1 = competitionId == "botola1"
    val title = if (isBotola1) "البطولة الاحترافية إنوي 1" else "البطولة الاحترافية إنوي 2"
    val teams = if (isBotola1) FakeFootballDataSource.botola1Teams else FakeFootballDataSource.botola2Teams
    val standings = if (isBotola1) FakeFootballDataSource.botola1Standings else FakeFootballDataSource.botola2Standings

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Tabs: Standings, Schedule, Teams
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("الترتيب") })
                Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("المباريات") })
                Tab(selected = selectedTab == 2, onClick = { selectedTab = 2 }, text = { Text("الأندية (${teams.size})") })
            }

            when (selectedTab) {
                0 -> {
                    // Standings Table
                    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                        items(standings) { row ->
                            Card(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("${row.position}", fontWeight = FontWeight.Bold)
                                    Text(row.teamName, fontWeight = FontWeight.SemiBold)
                                    Text("لعب: ${row.played}")
                                    Text("نقاط: ${row.points}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                }
                            }
                        }
                    }
                }
                1 -> {
                    // Fixtures List
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("جدول مباريات البطولة الاحترافية (DEMO DATA)")
                    }
                }
                2 -> {
                    // 16 Teams Grid/List
                    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                        items(teams) { team ->
                            Card(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(team.name, fontWeight = FontWeight.Bold)
                                        Text("المدينة: ${team.city} · الملعب: ${team.stadium}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                                    }
                                    IconButton(onClick = {}) {
                                        Icon(Icons.Default.Star, contentDescription = "Favorite")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
