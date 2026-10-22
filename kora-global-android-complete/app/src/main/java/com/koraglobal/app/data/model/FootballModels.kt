package com.koraglobal.app.data.model

enum class MatchStatus { LIVE, SCHEDULED, FINISHED }

data class Team(
    val id: String,
    val name: String,
    val city: String,
    val stadium: String,
    val logoColor: String = "#059669"
)

data class Match(
    val id: String,
    val competitionName: String,
    val round: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val homeScore: Int = 0,
    val awayScore: Int = 0,
    val status: MatchStatus,
    val minute: Int = 0,
    val kickoffTime: String,
    val stadium: String
)

data class StandingRow(
    val position: Int,
    val teamName: String,
    val played: Int,
    val won: Int,
    val drawn: Int,
    val lost: Int,
    val points: Int
)
