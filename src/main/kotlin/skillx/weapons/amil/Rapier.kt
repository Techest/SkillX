package skillx.weapons.amil

import skillx.core.interfaces.IWeapon
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.event.player.PlayerInteractEvent
import skillx.utils.SkillHandler

object Rapier : IWeapon {

    override val name = "Rapier"

    override val description = "Rapier"

    override val item = Material.STICK

    override fun onLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.drawLineWithDamage(5, Particle.SONIC_BOOM, player)
    }

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.drawLineWithDamage(5, Particle.SONIC_BOOM, player)

        player.velocity = player.velocity.add(player.location.direction.multiply(-2))
        return
    }

    override fun onShiftRightClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.drawLineWithVelocity(5, Particle.SONIC_BOOM, player, 2)
    }
}