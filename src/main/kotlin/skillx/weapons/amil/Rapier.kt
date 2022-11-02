package skillx.weapons.amil

import skillx.core.interfaces.IWeapon
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent

object Rapier : IWeapon {

    override val name = "Rapier"

    override val description = "Rapier"

    override val item = Material.FLINT

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        event.player.sendMessage("test success")
    }
}