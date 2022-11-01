package skillx.content.commands

import net.minecraft.Util
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Utility
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import javax.swing.text.Utilities

val inv: Inventory = Bukkit.createInventory(null, 1 * 9)

val item1 = createItem("Sword", Material.IRON_SWORD)
val item2 = createItem("Bard", Material.GOLDEN_SWORD)
val item3 = createItem("Rapier", Material.FLINT)

object SelectCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(sender !is Player) return false

        when(args?.get(0)) {
            "weapon" -> {
                inv.setItem(1, item1)
                inv.setItem(4, item2)
                inv.setItem(7, item3)
                sender.openInventory(inv)
            }
        }

        return true
    }
}

private fun createItem(name: String, mat: Material): ItemStack {
    val item = ItemStack(mat, 1)
    val meta = item.itemMeta
    meta.setDisplayName(name)
    item.setItemMeta(meta)
    return item
}