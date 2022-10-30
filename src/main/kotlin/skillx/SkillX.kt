package skillx

import org.bukkit.plugin.java.JavaPlugin
import skillx.skills.TestWeapon
import skillx.handlers.WeaponHandler

class SkillX : JavaPlugin() {

    override fun onEnable() {
        logger.info("SkillX Enable!")

        WeaponHandler.register(TestWeapon)

        WeaponHandler.weapons.keys.forEach { name ->
            logger.info("$name is registed.")
        }

        WeaponHandler.initWeapons(this)
    }

    override fun onDisable() {

    }

}