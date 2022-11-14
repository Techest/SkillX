package skillx.inventory

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import skillx.core.handlers.WeaponHandler

val skDebugInventory: Inventory =
    Bukkit.createInventory(null, 6 * 9, Component.text("Debug Inventory"))
        .apply { WeaponHandler.weapons.values.forEach { w -> ItemStack(w.item, 1) } }