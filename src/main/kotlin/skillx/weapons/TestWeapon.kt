package skillx.weapons

import skillx.core.IWeapon
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent


object TestWeapon : IWeapon {

    override val name = "Test Weapon"
    override val description = "For Test"
    override val item = Material.WOODEN_AXE

    override fun onLeftClick(event: PlayerInteractEvent) {
        event.player.sendMessage("Happy Coding!")
    }

}