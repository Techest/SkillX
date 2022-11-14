package skillx.weapons.amil

import skillx.core.IWeapon
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.event.player.PlayerInteractEvent
import skillx.core.SkillHandler

object Rapier : IWeapon {

    override val name = "Rapier"

    override val description = "Rapier"

    override val item = Material.STICK

    override fun onLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.drawLineWithExecuteFunc(player, 5, Particle.SONIC_BOOM) { e ->
            if (e !is LivingEntity) return@drawLineWithExecuteFunc
            if(e != player) e.damage(5.0)
        }
    }

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.drawLineWithExecuteFunc(player, 5, Particle.SONIC_BOOM) { e ->
            if (e !is LivingEntity) return@drawLineWithExecuteFunc
            if(e != player) e.damage(5.0)
        }

        player.velocity = player.velocity.add(player.location.direction.multiply(-2))
        return
    }

    override fun onShiftRightClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.drawLineWithExecuteFunc(player, 5, Particle.SONIC_BOOM) { e ->
            if (e !is LivingEntity) return@drawLineWithExecuteFunc
            if(e != player) e.velocity =
                player.velocity.add(player.location.direction.multiply(2))
        }
    }
}