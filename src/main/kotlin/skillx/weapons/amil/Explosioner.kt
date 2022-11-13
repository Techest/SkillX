package skillx.weapons.amil

import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffectType
import skillx.core.interfaces.IWeapon
import skillx.utils.SkillHandler

object Explosioner: IWeapon {
    override val name = "Explosioner"

    override val description = "Explosioner Skill"

    override val item = Material.GUNPOWDER

    override fun onLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.particleWithDamage(8, Particle.EXPLOSION_LARGE, player, 10, 5.0)
    }

    override fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        player.spawnParticle(
            Particle.EXPLOSION_NORMAL, loc, 10, 8.0, 1.0, 8.0
        )

        player.world.getNearbyEntities(loc, 8.0, 1.0, 8.0).forEach { target ->
            if (target is LivingEntity) if (target != player) target.damage(3.0, player)
        }

        player.velocity = player.velocity.add(player.location.direction.multiply(2))
    }

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.particleWithDamage(8, Particle.EXPLOSION_LARGE, player, 10, 5.0)
        SkillHandler.particleWithEffect(8, Particle.ELECTRIC_SPARK, player, 50, PotionEffectType.POISON, 100, 1)
    }
}