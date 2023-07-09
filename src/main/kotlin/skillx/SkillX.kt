package skillx

import org.bukkit.plugin.java.JavaPlugin
import skillx.weapon.handlers.WeaponHandler
import skillx.weapon.weapons.ExampleWeapon

@Suppress("unused")
class SkillX : JavaPlugin() {

    companion object {
        lateinit var instance: SkillX
            private set
    }

    override fun onEnable() {
        instance = this
        logger.info("Example Plugin Enabled!")

    }

    override fun onDisable() {
        logger.info("Example Plugin Disabled!")
    }

    private fun registerWeapons() {
        WeaponHandler.register(
            ExampleWeapon
        )

        WeaponHandler.weapons.keys.forEach {
            logger.info("$it is registered.")
        }


        WeaponHandler.initWeapons(this)
    }

}
