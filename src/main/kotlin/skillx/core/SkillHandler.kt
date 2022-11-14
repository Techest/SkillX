package skillx.core

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object SkillHandler {

    fun drawLine(player: Player, reach: Int, particle: Particle): ArrayList<Location> {
        val loc = player.location
        val usedLoc: ArrayList<Location> = ArrayList()

        for (i in 1..reach) {
            val particleLoc = Location(
                player.world,
                loc.x + (player.eyeLocation.direction.x * i),
                loc.y + (player.eyeLocation.direction.y * i),
                loc.z + (player.eyeLocation.direction.z * i)
            )
            usedLoc.add(particleLoc)
            player.world.spawnParticle(particle, particleLoc, reach)
        }
        return usedLoc
    }

    /**
     * @param reach 1 = one block
     */
    fun drawLineWithExecuteFunc(player: Player, reach: Int, particle: Particle, func: (entity: Entity?) -> Unit) {
        drawLine(player, reach, particle).forEach { loc ->
            player.world.getNearbyEntities(loc, 1.0, 1.0, 1.0).forEach { entity: Entity? ->
                func(entity)
            }
        }
    }

    fun forwardParticle(player: Player, particle: Particle, range: Int, distance: Double): Location {
        val loc = player.location
        val particleLoc = Location(
            player.world,
            loc.x + (player.eyeLocation.direction.x * distance),
            loc.y + (player.eyeLocation.direction.y),
            loc.z + (player.eyeLocation.direction.z * distance)
        )
        player.world.spawnParticle(particle, particleLoc, range)
        return particleLoc
    }

    fun forwardParticleExcuteFunc(
        player: Player,
        particle: Particle,
        range: Int,
        distance: Double,
        func: (entity: Entity?) -> Unit,
    ) {
        player.world.getNearbyEntities(forwardParticle(player, particle, range, distance), distance, 1.0, distance)
            .forEach { entity: Entity? ->
                func(entity)
            }
    }

}