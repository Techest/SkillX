package skillx.weapons.amil

import org.bukkit.entity.LivingEntity
import skillx.core.IWeapon
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.event.player.PlayerInteractEvent
import skillx.core.SkillHandler


object Kight : IWeapon {

    override val name = "Sword"

    override val description = "Sword Skill"

    override val item = Material.IRON_SWORD

    override fun onLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        player.playSound(player.location, Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1.0f, 1.0f)

        SkillHandler.drawLineWithExecuteFunc(player, 5, Particle.SWEEP_ATTACK) { entity ->
            if (entity !is LivingEntity) return@drawLineWithExecuteFunc
            if(entity != player) entity.damage(5.0)
        }
    }

    override fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.forwardParticleExcuteFunc(player, Particle.EXPLOSION_NORMAL, 1, 5) {entity ->
            if (entity !is LivingEntity) return@forwardParticleExcuteFunc
            if(entity != player) entity.damage(5.0)
        }
    }

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.forwardParticleExcuteFunc(player, Particle.EXPLOSION_NORMAL, 1, 5) {entity ->
            if (entity !is LivingEntity) return@forwardParticleExcuteFunc
            if(entity != player) entity.velocity = player.velocity.add(player.location.direction.multiply(2))
        }
    }

    override fun onShiftRightClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.drawLineWithExecuteFunc(player, 5, Particle.SWEEP_ATTACK) { entity ->
            if (entity !is LivingEntity) return@drawLineWithExecuteFunc
            if(entity != player) entity.damage(5.0)
        }

        player.velocity = player.velocity.add(player.location.direction.multiply(2))
        return
    }

}