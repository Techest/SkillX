package skillx.weapons.amil

import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import skillx.core.IWeapon
import skillx.core.SkillHandler

object Explosioner: IWeapon {
    override val name = "Explosioner"

    override val description = "Explosioner Skill"

    override val item = Material.GUNPOWDER

    override fun onLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.forwardParticleExcuteFunc(player, Particle.EXPLOSION_LARGE, 10, 8) {entity ->
            if (entity !is LivingEntity) return@forwardParticleExcuteFunc
            if(entity != player) entity.damage(5.0)
        }
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

        SkillHandler.forwardParticleExcuteFunc(player, Particle.EXPLOSION_LARGE, 10, 8) {entity ->
            if (entity !is LivingEntity) return@forwardParticleExcuteFunc
            if(entity != player) entity.damage(5.0)
        }
        SkillHandler.forwardParticleExcuteFunc(player, Particle.ELECTRIC_SPARK, 50, 8) {entity ->
            if (entity !is LivingEntity) return@forwardParticleExcuteFunc
            if(entity != player) entity.addPotionEffect(
                PotionEffect(
                    PotionEffectType.POISON,
                    100,
                    1
                )
            )
        }
    }
}