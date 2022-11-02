package skillx.utils

import com.mojang.math.Vector3f
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3

fun drawParticle(level: Level, from: Vec3, to: Vec3, density: Int, r: Float, g: Float, b: Float, scale: Float) {
    val particleOption = DustParticleOptions(Vector3f(r, g, b), scale)
    val diff = to.subtract(from).scale(1.0 / density)
    repeat(density) {
        val spawnAt = from.add(diff.scale(it.toDouble()))
        (level as ServerLevel).sendParticles(particleOption, spawnAt.x, spawnAt.y, spawnAt.z, 1, 0.0, 0.0, 0.0, 0.0)
    }
}