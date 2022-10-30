package skillx.skills.amil

import IWeapon
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent

object Rapier : IWeapon {

    override val name = "Rapier"

    override val description = "Rapier"

    override val item = Material.FLINT

    @EventHandler
    override fun action(event: PlayerInteractEvent) {
        super.action(event)
    }

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        event.player.sendMessage("test success")
    }
}