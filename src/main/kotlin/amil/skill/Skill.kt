package amil.skill

import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin
import java.lang.Double.min

class Skill : JavaPlugin(), Listener {
    override fun onEnable() {
        logger.info("스타또")
        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
    }

    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        if (event.item?.type != Material.STICK) return

        when (event.action) {
            Action.LEFT_CLICK_BLOCK -> {

            }

            // Replace block to log
            Action.RIGHT_CLICK_BLOCK -> {
                event.clickedBlock?.type = Material.BIRCH_LOG
            }

            // spawn particle
            Action.LEFT_CLICK_AIR -> {
                player.health = min(player.health + 10, 20.0) // <- 스파게티 방쉭
                player.spawnParticle(
                    Particle.HEART,
                    loc.x + (player.eyeLocation.direction.x * 5),
                    loc.y,
                    loc.z + (player.eyeLocation.direction.z * 5),
                    10,
                    0.5,
                    0.5,
                    0.5
                )
            }

            // dash
            Action.RIGHT_CLICK_AIR -> {
                player.velocity = player.velocity.add(player.location.direction.multiply(2))
                player.spawnParticle(Particle.PORTAL, player.location, 1000, 1.0, 1.0, 1.0)
            }

            Action.PHYSICAL -> return
        }

        // 허공 좌클릭 스킬 ( 검기 날리기 )
        if (
            player.isSneaking &&
            event.action == Action.LEFT_CLICK_AIR || event.action == Action.LEFT_CLICK_BLOCK
        ) {
            player.sendMessage("Shift + Left Click")
        }
    }

}