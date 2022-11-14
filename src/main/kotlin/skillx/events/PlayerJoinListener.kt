package skillx.events

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot


val userBoard = Bukkit.getScoreboardManager().newScoreboard

val objective = userBoard.registerNewObjective("kill", Criteria.TOTAL_KILL_COUNT, Component.text("Users"))

var killStat = 0

object PlayerJoinListener : Listener {

    init {
        objective.displaySlot = DisplaySlot.SIDEBAR
    }

    @EventHandler
    fun joinChecker(event: PlayerJoinEvent) {
        val score = objective.getScore(event.player.name)
        event.player.scoreboard = userBoard
        score.score = 0
        event.player.sendMessage(event.player.name + " Hello!")
    }

    @EventHandler
    fun leaveChecker(event: PlayerQuitEvent) {
        userBoard.resetScores(event.player.name)
    }

}

