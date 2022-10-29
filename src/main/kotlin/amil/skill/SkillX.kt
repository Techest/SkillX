package amil.skill

import amil.skill.etc.JoinCheckerForScore
import amil.skill.etc.ScoreCommand
import amil.skill.utils.UserListDebugCommand
import amil.skill.utils.UserListHandler
import amil.skill.weapon.Bard
import amil.skill.weapon.Rapier
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class SkillX : JavaPlugin() {

    override fun onEnable() {
        logger.info("Skill Plugins Enabled")
        // test
        server.pluginManager.registerEvents(TestSkill, this)

        // weapons
        server.pluginManager.registerEvents(Rapier, this)

        server.pluginManager.registerEvents(Bard, this)

        // score
        server.pluginManager.registerEvents(UserListHandler, this)
        this.getCommand("userlist")?.setExecutor(UserListDebugCommand)
    }

    override fun onDisable() {
        logger.info("Skill Plugins Disabled")
    }

}