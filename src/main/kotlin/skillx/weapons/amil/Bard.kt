package skillx.weapons.amil

import skillx.core.IWeapon
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import skillx.core.SkillHandler

object Bard : IWeapon {

    override val name = "Bard"
    override val description = "Bard"

    override val item = Material.GOLDEN_SWORD

    override fun onLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.drawLineWithExecuteFunc(player, 5, Particle.SPELL_WITCH) { entity ->
            if (entity !is LivingEntity) return@drawLineWithExecuteFunc
            if(entity != player) entity.damage(5.0)
        }
    }

    // 우클릭 스킬
    override fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        player.spawnParticle(
            Particle.HEART, loc, 10, 5.0, 1.0, 5.0
        )

        player.getNearbyEntities(5.0, 1.0, 5.0)

        player.world.getNearbyEntities(loc, 5.0, 1.0, 5.0).forEach { target ->
            if (target is LivingEntity) target.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 20, 4))
        }
    }

    // 쉬좌 스킬
    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        player.spawnParticle(
            Particle.HEART, loc, 10, 8.0, 1.0, 8.0
        )

        player.getNearbyEntities(8.0, 1.0, 8.0)

        player.world.getNearbyEntities(loc, 5.0, 1.0, 5.0).forEach { target ->
            if (target is LivingEntity) if (target != player) target.addPotionEffect(PotionEffect(PotionEffectType.WITHER, 100, 1))
        }
    }

    // 쉬우 스킬
    override fun onShiftRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        player.spawnParticle(
            Particle.HEART, loc, 10, 8.0, 1.0, 8.0
        )

        player.getNearbyEntities(8.0, 1.0, 8.0)

        player.world.getNearbyEntities(loc, 5.0, 1.0, 5.0).forEach { target ->
            if (target is LivingEntity) target.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1))
        }
    }

}