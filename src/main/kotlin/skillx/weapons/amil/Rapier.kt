package skillx.weapons.amil

import org.bukkit.entity.LivingEntity
import org.bukkit.Location
import skillx.core.interfaces.IWeapon
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.event.player.PlayerInteractEvent

object Rapier : IWeapon {

    override val name = "Rapier"

    override val description = "Rapier"

    override val item = Material.STICK

    override fun onLeftClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

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

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

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

        player.velocity = player.velocity.add(player.location.direction.multiply(-2))
        return
    }
}