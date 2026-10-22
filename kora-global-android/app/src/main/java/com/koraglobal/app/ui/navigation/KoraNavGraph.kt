package com.koraglobal.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.koraglobal.app.R
import com.koraglobal.app.ui.screens.*

sealed class Screen(val route: String, val icon: @Composable () -> Unit, val titleRes: Int) {
    object Home : Screen("home", { Icon(Icons.Default.SportsSoccer, contentDescription = null) }, R.string.tab_matches)
    object Competitions : Screen("competitions", { Icon(Icons.Default.EmojiEvents, contentDescription = null) }, R.string.tab_competitions)
    object Standings : Screen("standings", { Icon(Icons.Default.Leaderboard, contentDescription = null) }, R.string.tab_standings)
    object Favorites : Screen("favorites", { Icon(Icons.Default.Star, contentDescription = null) }, R.string.tab_favorites)
    object LiveTv : Screen("live_tv", { Icon(Icons.Default.Tv, contentDescription = null) }, R.string.tab_live_tv)
    object Settings : Screen("settings", { Icon(Icons.Default.Settings, contentDescription = null) }, R.string.tab_settings)
}

@Composable
fun KoraNavGraph(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    language: String,
    onLanguageChange: (String) -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Competitions,
        Screen.Standings,
        Screen.Favorites,
        Screen.LiveTv,
        Screen.Settings
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = screen.icon,
                        label = { Text(stringResource(screen.titleRes)) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onMatchClick = { matchId ->
                    navController.navigate("match_detail/$matchId")
                })
            }
            composable(Screen.Competitions.route) {
                CompetitionsScreen(onCompetitionClick = { compId ->
                    navController.navigate("competition_detail/$compId")
                })
            }
            composable(Screen.Standings.route) {
                StandingsScreen()
            }
            composable(Screen.Favorites.route) {
                FavoritesScreen(onMatchClick = { matchId ->
                    navController.navigate("match_detail/$matchId")
                })
            }
            composable(Screen.LiveTv.route) {
                LiveTvScreen()
            }
            composable(Screen.Settings.route) {
                SettingsScreen(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = onToggleTheme,
                    language = language,
                    onLanguageChange = onLanguageChange
                )
            }
            composable("competition_detail/{compId}") { backStackEntry ->
                val compId = backStackEntry.arguments?.getString("compId") ?: "botola1"
                BotolaDetailScreen(
                    competitionId = compId,
                    onBack = { navController.popBackStack() },
                    onMatchClick = { matchId -> navController.navigate("match_detail/$matchId") }
                )
            }
            composable("match_detail/{matchId}") { backStackEntry ->
                val matchId = backStackEntry.arguments?.getString("matchId") ?: ""
                MatchDetailScreen(
                    matchId = matchId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
