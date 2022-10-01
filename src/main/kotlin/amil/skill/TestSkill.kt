package amil.skill

import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import kotlin.math.min

object TestSkill : Listener {

    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        if (event.item?.type != Material.STICK) return

        // 허공 좌클릭 스킬 ( 검기 날리기 )
        if (player.isSneaking &&
            event.action == Action.LEFT_CLICK_AIR || event.action == Action.LEFT_CLICK_BLOCK) {
            for (i in 1..5)
                player.spawnParticle(
                    Particle.SONIC_BOOM,
                    loc.x + (player.eyeLocation.direction.x * i),
                    loc.y + (player.eyeLocation.direction.y * i),
                    loc.z + (player.eyeLocation.direction.z * i),
                    1
                )

            player.velocity = player.velocity.add(player.location.direction.multiply(-2))

            return
        }

        when (event.action) {
            Action.LEFT_CLICK_BLOCK -> return

            // Replace block to log
            Action.RIGHT_CLICK_BLOCK ->
                event.clickedBlock?.type = Material.BIRCH_LOG

            // spawn particle
            Action.LEFT_CLICK_AIR -> {
                player.health = min(player.health + 10, 20.0) // <- 스파게티 방쉭
                for (i in 1..20)
                    player.spawnParticle(
                        Particle.SONIC_BOOM,
                        loc.x + (player.eyeLocation.direction.x * i),
                        loc.y + (player.eyeLocation.direction.y * i),
                        loc.z + (player.eyeLocation.direction.z * i),
                        1
                    )

                player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1.0f, 10.0f)
            }

            // dash
            Action.RIGHT_CLICK_AIR -> {
                player.velocity = player.velocity.add(player.location.direction.multiply(2))
                player.spawnParticle(Particle.PORTAL, player.location, 1000, 1.0, 1.0, 1.0)
            }

            Action.PHYSICAL -> return
        }
    }

}