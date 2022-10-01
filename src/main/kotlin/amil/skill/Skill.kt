package amil.skill

import amil.skill.weapon.Rapier
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Skill : JavaPlugin() {

    override fun onEnable() {
        logger.info("Skill Plugins Enabled")
        // test
        server.pluginManager.registerEvents(TestSkill, this)

        // weapons
        server.pluginManager.registerEvents(Rapier, this)
    }

    override fun onDisable() {
        logger.info("Skill Plugins Disabled")
    }

}