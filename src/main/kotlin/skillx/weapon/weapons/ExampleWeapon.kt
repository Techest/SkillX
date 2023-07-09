package skillx.weapon.weapons

import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import skillx.weapon.interfaces.IWeapon

object ExampleWeapon : IWeapon {

    override val name = "Example Shovel"
    override val description = "Example Weapon Implement"
    override val item = Material.GOLDEN_SHOVEL

    override fun onLeftClick(event: PlayerInteractEvent) {
        event.player.sendPlainMessage("Hello Player!")
    }
}