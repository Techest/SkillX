/**
 * 스코어보드에 플레이어 목록 표시
 */
package amil.skill.utils

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot


private val board = Bukkit.getScoreboardManager().newScoreboard

val objective = board.registerNewObjective("123", Criteria.DUMMY, Component.text("Users"))

object UserListHandler : Listener {

    init {
        objective.displaySlot = DisplaySlot.SIDEBAR
    }

    @EventHandler
    fun joinChecker(event: PlayerJoinEvent) {
        val score = objective.getScore(event.player.name)
        event.player.scoreboard = board
        score.score = 0
        event.player.sendMessage(event.player.name + " Hello!")
    }

    @EventHandler
    fun leaveChecker(event: PlayerQuitEvent) {
        board.resetScores(event.player.name)
    }

}

object UserListDebugCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (args == null) return true

        when (args[0]) {
            "refresh" -> {
                sender.scoreboard = board
                sender.sendMessage("refresh scoreboard")
            }
        }
        return true
    }

}