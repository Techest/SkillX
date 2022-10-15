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

    override val item = Material.STICK

    private var spawnedArmorStand = ArrayList<Entity>()

    @EventHandler
    fun fallCancel(event: EntityDamageEvent) {
        if (event.entity !is Player) return
        if ((event.entity as Player).player?.inventory?.itemInMainHand?.type != Material.STICK) return

        val player = (event.entity as Player).player ?: return

        if (event.cause == EntityDamageEvent.DamageCause.FALL) {
            event.isCancelled = true
            player.spawnParticle(Particle.BUBBLE_POP, player.location, 1000)
        }
    }

    @EventHandler
    override fun action(event: PlayerInteractEvent) {
        super.action(event)
    }

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        // 허공 좌클릭 스킬 ( 검기 날리기 )

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

    override fun onLeftClickBlock(event: PlayerInteractEvent) {
        event.clickedBlock?.type = Material.BIRCH_LOG
    }

    override fun onLeftClickAir(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location
        player.health = min(player.health + 10, 20.0) // <- 스파게티 방쉭
        for (i in 1..20) {
            player.spawnParticle(
                Particle.SONIC_BOOM,
                loc.x + (player.eyeLocation.direction.x * i),
                loc.y + (player.eyeLocation.direction.y * i),
                loc.z + (player.eyeLocation.direction.z * i),
                1
            )

            val amLoc = Location(
                player.world,
                loc.x + (player.eyeLocation.direction.x * i),
                loc.y + (player.eyeLocation.direction.y * i),
                loc.z + (player.eyeLocation.direction.z * i)
            )

            spawnedArmorStand.add(player.world.spawnEntity(amLoc, EntityType.ARMOR_STAND))
        }

        spawnedArmorStand.forEach { entity ->
            val armorStand = (entity as LivingEntity)
            armorStand.isInvisible = true
            armorStand.setGravity(false)

            // Damage
            entity.world.getNearbyEntities(entity.location, 1.0, 1.0, 1.0)
                .forEach { target ->
                    if (target is LivingEntity) {
                        target.damage(5.0)
                        entity.remove()
                    }

                }
        }

        player.playSound(player.location, Sound.ENTITY_WARDEN_SONIC_BOOM, 1.0f, 10.0f)
    }

    @EventHandler
    fun godArmorStand(event: EntityDamageEvent) {
        if(event.entity is ArmorStand) {
            spawnedArmorStand.forEach { entity -> if(event.entity == entity) event.isCancelled = true }
        }
    }

    override fun onRightClickAir(event: PlayerInteractEvent) {
        val player = event.player
        player.velocity = player.velocity.add(player.location.direction.multiply(2))
        player.spawnParticle(Particle.PORTAL, player.location, 1000, 1.0, 1.0, 1.0)
    }

}