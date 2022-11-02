package skillx.weapons.tmvkrpxl0

import skillx.core.interfaces.IWeapon
import com.mojang.math.Vector3f
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ProjectileUtil
import net.minecraft.world.level.ClipBlockStateContext
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.scheduler.BukkitRunnable
import skillx.SkillX
import skillx.core.projectiles.HomingArrow
import java.util.Stack

object HomingTurret : IWeapon {
    override val name = "유도 터렛"
    override val description = "바라보고 있는 적을 지지고 볶습니다"
    override val item = Material.IRON_INGOT

    override fun onLeftClickAir(event: PlayerInteractEvent) {
        val player = (event.player as CraftPlayer).handle

        val start = player.eyePosition
        val end = player.eyePosition.add(player.lookAngle.scale(200.0))

        val lookingAt = ProjectileUtil.getEntityHitResult(
            player.level,
            player,
            start,
            end,
            AABB.ofSize(player.position(), 400.0, 400.0, 400.0),
        ) { entity ->
            entity is LivingEntity && entity.isAlive
        } ?: return

        val blockedResult = player.level.isBlockInLine(ClipBlockStateContext(start, end) {
            !it.isAir
        })

        if (blockedResult.type != HitResult.Type.MISS &&
            start.distanceToSqr(
                Vec3(
                    blockedResult.blockPos.x.toDouble(),
                    blockedResult.blockPos.y.toDouble(),
                    blockedResult.blockPos.z.toDouble()
                )
            ) < start.distanceToSqr(lookingAt.location)
        ) return

        val spawnPosLocal = Stack<Vector3f>()
        listOf(
            Vector3f(1F, 0.2F, 1F),
            Vector3f(-1F, 0.2F, 1F),
            Vector3f(1F, -0.8F, 1F),
            Vector3f(-1F, -0.8F, 1F),
            Vector3f(1F, -0.4F, 1F),
            Vector3f(-1F, -0.4F, 1F)
        ).forEach { spawnPosLocal.push(it) }


        val toHeadSpace = Vector3f.YN.rotationDegrees(player.yRot)
        toHeadSpace.mul(Vector3f.XP.rotationDegrees(player.xRot))

        val offset = player.eyePosition

        object : BukkitRunnable() {
            override fun run() {
                repeat(2) {
                    if (spawnPosLocal.empty()) {
                        cancel()
                        return@repeat
                    }
                    val pos = spawnPosLocal.pop()
                    pos.transform(toHeadSpace)
                    val spawnAt = Vec3(pos).add(offset)
                    val homingArrow = HomingArrow(
                        player.level,
                        spawnAt.x(), spawnAt.y(), spawnAt.z(),
                        lookingAt.entity as LivingEntity,
                        10
                    )
                    homingArrow.deltaMovement =
                        lookingAt.entity.position().subtract(homingArrow.position()).normalize().scale(2.0)
                    homingArrow.isNoGravity = true
                    player.level.addFreshEntity(homingArrow)
                }
            }
        }.runTaskTimer(SkillX.instance, 0, 2)
    }
}