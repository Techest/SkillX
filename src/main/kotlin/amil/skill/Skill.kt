package amil.skill

import amil.skill.weapon.Bard
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

        server.pluginManager.registerEvents(Bard, this)
    }

    override fun onDisable() {
        logger.info("Skill Plugins Disabled")
    }

}