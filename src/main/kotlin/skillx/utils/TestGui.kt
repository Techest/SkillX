package amil.skill.utils

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.PlayerInventory

val inv_rows = 4 * 9
val inv: Inventory = Bukkit.createInventory(null, inv_rows, "Test Gui")

object TestGuiCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(sender !is Player) return false

        sender.openInventory(inv)

        return true
    }


}