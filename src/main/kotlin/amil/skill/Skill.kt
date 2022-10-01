package amil.skill

import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Skill : JavaPlugin() {

    override fun onEnable() {
        logger.info("Skill Plugins Enabled")
        server.pluginManager.registerEvents(TestSkill, this)
    }

    override fun onDisable() {
        logger.info("Skill Plugins Disabled")
    }

}