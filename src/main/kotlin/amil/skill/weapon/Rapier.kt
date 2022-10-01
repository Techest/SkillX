package amil.skill.weapon

import amil.skill.base.IWeapon
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent

object Rapier : IWeapon {

    override val item = Material.FLINT

    @EventHandler
    override fun action(event: PlayerInteractEvent) {
        super.action(event)
    }

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        event.player.sendMessage("test success")
    }
}