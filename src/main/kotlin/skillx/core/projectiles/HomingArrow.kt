package skillx.core.projectiles

import com.mojang.math.Vector3f
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.Arrow
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.Vec3
import skillx.utils.angle
import skillx.utils.invert
import skillx.utils.radian
import skillx.utils.rotateByAxis
import kotlin.math.absoluteValue

var MAX_TURNING_ANGLE = (3.1415927F / 12).radian()
var IGNORE_INVULTIME = true

class HomingArrow(
    world: Level,
    x: Double,
    y: Double,
    z: Double,
    var target: LivingEntity? = null,
    var followDelay: Int,
) : Arrow(world, x, y, z) {
    override fun tick() {
        super.tick()
        if (followDelay > 0) followDelay--

        if (inGround) target = null

        if (followDelay == 0) {
            if (target?.isAlive == true) {
                val arrowToTarget = target!!.position().subtract(this.position())

                val axis = Vector3f(deltaMovement.cross(arrowToTarget))
                axis.normalize()

                val movement = Vector3f(deltaMovement)

                var betweenAngle = movement.angle(axis)

                if (betweenAngle.radian.absoluteValue > MAX_TURNING_ANGLE.radian) {
                    betweenAngle = if (betweenAngle.radian > 0) MAX_TURNING_ANGLE else MAX_TURNING_ANGLE.invert()
                }

                val rotated = Vec3(rotateByAxis(movement, axis, betweenAngle))
                deltaMovement = rotated.scale(1 / 0.99)
            } else isNoGravity = false
        }
    }

    override fun onHitEntity(entityHitResult: EntityHitResult) {
        super.onHitEntity(entityHitResult)

        if (IGNORE_INVULTIME) entityHitResult.entity.invulnerableTime = 0
    }
}

/*
class Test: Listener {
    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val player = (event.player as CraftPlayer).handle

        val nearestPig = player.level
            .getEntities(player, AABB.ofSize(player.position(), 500.0, 500.0, 500.0))
            .firstOrNull { it is Pig} as Pig? ?: return
        val spawnLocation = player.position().add(player.lookAngle.scale(3.0))

        val homingArrow = HomingArrow(player.level, spawnLocation.x, spawnLocation.y, spawnLocation.z, nearestPig)
        homingArrow.deltaMovement = player.lookAngle.scale(2.0)

        player.level.addFreshEntity(homingArrow)
    }
}*/
