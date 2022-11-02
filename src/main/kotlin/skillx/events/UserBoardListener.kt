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

val objective = userBoard.registerNewObjective("123", Criteria.DUMMY, Component.text("Users"))

object UserBoardListener : Listener {

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

