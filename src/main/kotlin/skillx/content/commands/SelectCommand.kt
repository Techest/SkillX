package skillx.content.commands

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import skillx.core.handlers.WeaponHandler

val select_inv: Inventory = Bukkit.createInventory(null, 6 * 9)

object SelectCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        when (args?.get(0)) {
            "weapon" -> {
                var count = 0
                WeaponHandler.weapons.values.forEach { wea ->
                    select_inv.setItem(count, createItem(wea.name, wea.item))
                    count = +1
                }
                sender.openInventory(select_inv)
            }
        }

        return true
    }
}

private fun createItem(name: String, mat: Material): ItemStack {
    val item = ItemStack(mat, 1)
    val meta = item.itemMeta
    meta.displayName(Component.text(name))
    item.itemMeta = meta
    return item
}