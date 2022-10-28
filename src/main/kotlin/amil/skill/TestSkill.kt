package amil.skill

import amil.skill.base.IWeapon
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent
import kotlin.math.min

object TestSkill : IWeapon {

    override val item = Material.IRON_SWORD

    private var spawnedArmorStand = ArrayList<Entity>()

    @EventHandler
    override fun action(event: PlayerInteractEvent) {
        super.action(event)
    }

    // 좌클릭 스킬
    override fun onLeftClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        player.playSound(player.location, Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1.0f, 1.0f)

        for (i in 1..5) {
            player.spawnParticle(
                Particle.SONIC_BOOM,
                loc.x + (player.eyeLocation.direction.x * i),
                loc.y + (player.eyeLocation.direction.y * i),
                loc.z + (player.eyeLocation.direction.z * i),
                1
            )

            val damageLoc = Location(
                player.world,
                loc.x + (player.eyeLocation.direction.x * i),
                loc.y + (player.eyeLocation.direction.y * i),
                loc.z + (player.eyeLocation.direction.z * i)
            )

            player.world.getNearbyEntities(damageLoc, 1.0, 1.0, 1.0).forEach { target ->
                    if (target is LivingEntity) if (target != player) target.damage(5.0, player)
                }
        }
    }

    // 우클릭 스킬
    override fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location
        player.spawnParticle(Particle.EXPLOSION_NORMAL,
            loc.x + (player.eyeLocation.direction.x * 5),
            loc.y + (player.eyeLocation.direction.y),
            loc.z + (player.eyeLocation.direction.z * 5),
            100)

        val damageLoc = Location(
                player.world,
            loc.x + (player.eyeLocation.direction.x * 5),
            loc.y + (player.eyeLocation.direction.y ),
            loc.z + (player.eyeLocation.direction.z * 5)
        )

        player.world.getNearbyEntities(damageLoc, 1.0, 1.0, 1.0).forEach { target ->
            if (target is LivingEntity) if (target != player) target.damage(5.0, player)
        }
    }

    // 쉬좌 스킬
    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location
        player.spawnParticle(Particle.EXPLOSION_NORMAL,
            loc.x + (player.eyeLocation.direction.x * 5),
            loc.y + (player.eyeLocation.direction.y),
            loc.z + (player.eyeLocation.direction.z * 5),
            100)

        val damageLoc = Location(
                player.world,
            loc.x + (player.eyeLocation.direction.x * 5),
            loc.y + (player.eyeLocation.direction.y ),
            loc.z + (player.eyeLocation.direction.z * 5)
        )

        player.world.getNearbyEntities(damageLoc, 1.0, 1.0, 1.0).forEach { target ->
            if (target is LivingEntity) if (target != player) target.velocity = player.velocity.add(player.location.direction.multiply(2))
        }
    }

    // 쉬우 스킬
    override fun onShiftRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        for (i in 1..5) {
            player.spawnParticle(
                    Particle.SONIC_BOOM,
                loc.x + (player.eyeLocation.direction.x * i),
                loc.y + (player.eyeLocation.direction.y * i),
                loc.z + (player.eyeLocation.direction.z * i),
                1)

            val damageLoc = Location(
                    player.world,
                loc.x + (player.eyeLocation.direction.x * i),
                loc.y + (player.eyeLocation.direction.y * i),
                loc.z + (player.eyeLocation.direction.z * i)
            )

            player.world.getNearbyEntities(damageLoc, 1.0, 1.0, 1.0).forEach { target ->
                if (target is LivingEntity) if (target != player) target.damage(5.0, player)
            }
        }

        player.velocity = player.velocity.add(player.location.direction.multiply(2))
        return
    }

}