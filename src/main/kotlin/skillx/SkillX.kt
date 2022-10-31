package skillx

import amil.skill.utils.TestGuiCommand
import amil.skill.utils.UserListDebugCommand
import amil.skill.utils.UserListHandler
import org.bukkit.plugin.java.JavaPlugin
import skillx.skills.TestWeapon
import skillx.handlers.WeaponHandler
import skillx.skills.amil.Bard
import skillx.skills.amil.Kight
import skillx.skills.amil.Rapier

class SkillX : JavaPlugin() {

    override fun onEnable() {
        logger.info("SkillX Enable!")

        ////////////////////////////////////////////////
        // ^----------[ Register Weapons ]----------^ //

        // For Test, Deafult Weapon
        WeaponHandler.register(TestWeapon)

        /**
         * @author DwarfAmil
         */
        WeaponHandler.register(
            Kight, // IRON_SWORD
            Rapier, // FLINT
            Bard // GOLDEN_SWORD
        )

        server.pluginManager.registerEvents(UserListHandler, this)
        this.getCommand("userlist")?.setExecutor(UserListDebugCommand)

        this.getCommand("gui")?.setExecutor(TestGuiCommand)

        WeaponHandler.weapons.keys.forEach { name ->
            logger.info("$name is registed.")
        }

        WeaponHandler.initWeapons(this)
        // ^----------[ Register Weapons ]----------^ //
        ////////////////////////////////////////////////
    }

    override fun onDisable() {

    }

}