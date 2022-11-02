package skillx.commands.debug

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import skillx.events.userBoard

object UserBoardDebug : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (args == null) return true

        when (args[0]) {
            "refresh" -> {
                sender.scoreboard = userBoard
                sender.sendMessage("refresh scoreboard")
            }
        }
        return true
    }

}