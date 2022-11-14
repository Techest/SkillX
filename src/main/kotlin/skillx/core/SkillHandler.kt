package skillx.core

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

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

    fun particleWithDamage(distance: Int, particle: Particle, player: Player, particleInt: Int, damage: Double) {
        val loc = player.location

        val skillLoc = Location(
            player.world,
            loc.x + (player.eyeLocation.direction.x * distance),
            loc.y + (player.eyeLocation.direction.y * distance),
            loc.z + (player.eyeLocation.direction.z * distance)
        )

        player.spawnParticle(particle, skillLoc.x, skillLoc.y, skillLoc.z, particleInt)

        player.world.getNearbyEntities(skillLoc, 3.0, 3.0, 3.0).forEach { target ->
            if (target is LivingEntity) if (target != player) target.damage(damage, player)
        }
    }

    fun particleWithVelocity(distance: Int, particle: Particle, player: Player, particleInt: Int, velocityInt: Int) {
        val loc = player.location

        val skillLoc = Location(
            player.world,
            loc.x + (player.eyeLocation.direction.x * distance),
            loc.y + (player.eyeLocation.direction.y * distance),
            loc.z + (player.eyeLocation.direction.z * distance)
        )

        player.spawnParticle(particle, skillLoc.x, skillLoc.y, skillLoc.z, particleInt)

        player.world.getNearbyEntities(skillLoc, 3.0, 3.0, 3.0).forEach { target ->
            if (target is LivingEntity) if (target != player) target.velocity =
                player.velocity.add(player.location.direction.multiply(velocityInt))
        }
    }

    fun particleWithEffect(
        distance: Int,
        particle: Particle,
        player: Player,
        particleInt: Int,
        effect: PotionEffectType,
        effectTime: Int,
        effectStrong: Int,
    ) {
        val loc = player.location

        val skillLoc = Location(
            player.world,
            loc.x + (player.eyeLocation.direction.x * distance),
            loc.y + (player.eyeLocation.direction.y * distance),
            loc.z + (player.eyeLocation.direction.z * distance)
        )

        player.spawnParticle(particle, skillLoc.x, skillLoc.y, skillLoc.z, particleInt)

        player.world.getNearbyEntities(skillLoc, 3.0, 3.0, 3.0).forEach { target ->
            if (target is LivingEntity) if (target != player) target.addPotionEffect(
                PotionEffect(
                    effect,
                    effectTime,
                    effectStrong
                )
            )
        }
    }

}