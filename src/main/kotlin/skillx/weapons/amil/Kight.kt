package skillx.weapons.amil

import skillx.core.interfaces.IWeapon
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.event.player.PlayerInteractEvent
import skillx.utils.SkillHandler


object Kight : IWeapon {

    override val name = "Sword"

    override val description = "Sword Skill"

    override val item = Material.IRON_SWORD

    override fun onLeftClick(event: PlayerInteractEvent) {
        val player = event.player
        val loc = player.location

        player.playSound(player.location, Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1.0f, 1.0f)

        SkillHandler.drawLineWithDamage(5, Particle.SWEEP_ATTACK, player)
    }

    override fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.particleWithDamage(5, Particle.EXPLOSION_NORMAL, player, 10, 5.0)
    }

    override fun onShiftLeftClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.particleWithVelocity(5, Particle.EXPLOSION_NORMAL, player, 10, 2)
    }

    override fun onShiftRightClick(event: PlayerInteractEvent) {
        val player = event.player

        SkillHandler.drawLineWithDamage(5, Particle.SWEEP_ATTACK, player)

        player.velocity = player.velocity.add(player.location.direction.multiply(2))
        return
    }

}