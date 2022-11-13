package skillx.utils

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

object SkillHandler {
    fun drawLineWithDamage(count: Int, particle: Particle, player: Player) {
        val loc = player.location

        for (i in 1..count) {
            val skillLoc = Location(
                player.world,
                loc.x + (player.eyeLocation.direction.x * i),
                loc.y + (player.eyeLocation.direction.y * i),
                loc.z + (player.eyeLocation.direction.z * i)
            )

            player.spawnParticle(particle, skillLoc.x, skillLoc.y, skillLoc.z, 1)

            player.world.getNearbyEntities(skillLoc, 1.0, 1.0, 1.0).forEach { target ->
                if (target is LivingEntity) if (target != player) target.damage(5.0, player)
            }
        }
    }

    fun drawLineWithVelocity(count: Int, particle: Particle, player: Player, velocityInt: Int) {
        val loc = player.location

        for (i in 1..count) {
            val skillLoc = Location(
                player.world,
                loc.x + (player.eyeLocation.direction.x * i),
                loc.y + (player.eyeLocation.direction.y * i),
                loc.z + (player.eyeLocation.direction.z * i)
            )

            player.spawnParticle(particle, skillLoc.x, skillLoc.y, skillLoc.z, 1)

            player.world.getNearbyEntities(skillLoc, 1.0, 1.0, 1.0).forEach { target ->
                if (target is LivingEntity) if (target != player) target.velocity =
                    player.velocity.add(player.location.direction.multiply(velocityInt))
            }
        }
    }
}