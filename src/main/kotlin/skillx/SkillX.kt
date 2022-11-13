package skillx

import org.bukkit.plugin.java.JavaPlugin
import skillx.commands.debug.SelectCommand
import skillx.commands.debug.SelectTabAutoComplete
import skillx.commands.debug.UserBoardDebug
import skillx.weapons.amil.Bard
import skillx.weapons.amil.Kight
import skillx.weapons.amil.Rapier
import skillx.weapons.tmvkrpxl0.HomingTurret
import skillx.events.SelectionClickListener
import skillx.events.UserBoardListener
import skillx.weapons.TestWeapon
import skillx.core.handlers.WeaponHandler
import skillx.weapons.amil.Explosioner

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
            Bard, // GOLDEN_SWORD
            Explosioner // GUNPOWDER
        )

        /**
         * @author tmvkrpxl0
         */
        WeaponHandler.register(
            HomingTurret // IRON_INGOT
        )

        // UserList
        server.pluginManager.registerEvents(UserBoardListener, this)
        this.getCommand("userlist")?.setExecutor(UserBoardDebug)

        // debug item selection
        server.pluginManager.registerEvents(SelectionClickListener, this)
        this.getCommand("select")?.setExecutor(SelectCommand)
        this.getCommand("select")?.tabCompleter = SelectTabAutoComplete

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