package amil.skill.weapon

import amil.skill.base.IWeapon
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Bard : IWeapon {

    override val item = Material.GOLDEN_SWORD

    @EventHandler
    override fun action(event: PlayerInteractEvent) {
        super.action(event)
    }

    // 우클릭 스킬
    override fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        player.spawnParticle(
                Particle.HEART, loc, 10, 5.0, 1.0, 5.0)

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
            Particle.HEART, loc, 10, 8.0, 1.0, 8.0)

        player.getNearbyEntities(8.0, 1.0, 8.0)

        player.world.getNearbyEntities(loc, 5.0, 1.0, 5.0).forEach { target ->
            if (target is LivingEntity) target.addPotionEffect(PotionEffect(PotionEffectType.WITHER, 100, 1))
        }
    }

    // 쉬우 스킬
    override fun onShiftRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        player.spawnParticle(
            Particle.HEART, loc, 10, 8.0, 1.0, 8.0)

        player.getNearbyEntities(8.0, 1.0, 8.0)

        player.world.getNearbyEntities(loc, 5.0, 1.0, 5.0).forEach { target ->
            if (target is LivingEntity) target.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1))
        }
    }
}