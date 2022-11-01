package skillx

import org.bukkit.plugin.java.JavaPlugin
import skillx.content.commands.SelectCommand
import skillx.content.weapons.amil.Bard
import skillx.content.weapons.amil.Kight
import skillx.content.weapons.amil.Rapier
import skillx.content.weapons.tmvkrpxl0.HomingTurret
import skillx.core.commands.UserListDebugCommand
import skillx.core.commands.UserListHandler
import skillx.weapons.TestWeapon
import skillx.core.handlers.WeaponHandler

class SkillX : JavaPlugin() {
    companion object {
        lateinit var instance: SkillX
    }

    override fun onEnable() {
        instance = this
        logger.info("SkillX Enable!")

        ////////////////////////////////////////////////
        // ^----------[ Register Weapons ]----------^ //

        // For Test, Default Weapon
        WeaponHandler.register(TestWeapon)

        /**
         * @author PleahMaCaka
         */
        WeaponHandler.register(
            // STUFF // STUFF
        )

        /**
         * @author DwarfAmil
         */
        WeaponHandler.register(
            Kight, // IRON_SWORD
            Rapier, // FLINT
            Bard // GOLDEN_SWORD
        )

        /**
         * @author tmvkrpxl0
         */
        WeaponHandler.register(
            HomingTurret // IRON_INGOT
        )

        // UserList
        server.pluginManager.registerEvents(UserListHandler, this)
        this.getCommand("userlist")?.setExecutor(UserListDebugCommand)

        // debug item selection
        this.getCommand("select")?.setExecutor(SelectCommand)

        WeaponHandler.weapons.keys.forEach { name ->
            logger.info("$name is register.")
        }

        WeaponHandler.initWeapons(this)
        // ^----------[ Register Weapons ]----------^ //
        ////////////////////////////////////////////////
    }

    override fun onDisable() {

    }

}