package skillx.events

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import skillx.core.WeaponHandler

object PreventGetDebugItem : Listener {

    @EventHandler
    fun onClicked(event: InventoryClickEvent) {
        if (event.inventory != WeaponHandler.weaponsInv) return

        event.isCancelled = true

        val player = event.whoClicked
        val clickItem = event.currentItem

        if (clickItem == null || clickItem.type == Material.AIR) return

        player.inventory.addItem(clickItem)
        player.closeInventory()

    }

}