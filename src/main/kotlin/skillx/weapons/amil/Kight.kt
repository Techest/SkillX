package skillx.weapons.amil

import skillx.core.interfaces.IWeapon
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.event.player.PlayerInteractEvent


object Kight : IWeapon {

    override val name = "Sword"

    override val description = "Sword Skill"

    override val item = Material.IRON_SWORD

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

    override fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location
        player.spawnParticle(
            Particle.EXPLOSION_NORMAL,
            loc.x + (player.eyeLocation.direction.x * 5),
            loc.y + (player.eyeLocation.direction.y),
            loc.z + (player.eyeLocation.direction.z * 5),
            100
        )

        val damageLoc = Location(
            player.world,
            loc.x + (player.eyeLocation.direction.x * 5),
            loc.y + (player.eyeLocation.direction.y),
            loc.z + (player.eyeLocation.direction.z * 5)
        )

        player.world.getNearbyEntities(damageLoc, 1.0, 1.0, 1.0).forEach { target ->
            if (target is LivingEntity) if (target != player) target.damage(5.0, player)
        }
    }

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location
        player.spawnParticle(
            Particle.EXPLOSION_NORMAL,
            loc.x + (player.eyeLocation.direction.x * 5),
            loc.y + (player.eyeLocation.direction.y),
            loc.z + (player.eyeLocation.direction.z * 5),
            100
        )

        val damageLoc = Location(
            player.world,
            loc.x + (player.eyeLocation.direction.x * 5),
            loc.y + (player.eyeLocation.direction.y),
            loc.z + (player.eyeLocation.direction.z * 5)
        )

        player.world.getNearbyEntities(damageLoc, 1.0, 1.0, 1.0).forEach { target ->
            if (target is LivingEntity) if (target != player) target.velocity =
                player.velocity.add(player.location.direction.multiply(2))
        }
    }

    override fun onShiftRightClick(event: PlayerInteractEvent) {
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

        player.velocity = player.velocity.add(player.location.direction.multiply(2))
        return
    }

}