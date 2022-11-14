package skillx

import org.bukkit.plugin.java.JavaPlugin
import skillx.commands.debug.SelectCommand
import skillx.commands.debug.SelectTabAutoComplete
import skillx.commands.debug.UserBoardDebug
import skillx.weapons.amil.Bard
import skillx.weapons.amil.Kight
import skillx.weapons.amil.Rapier
import skillx.weapons.tmvkrpxl0.HomingTurret
import skillx.events.PreventGetDebugItem
import skillx.events.PlayerJoinListener
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

        server.pluginManager.registerEvents(PlayerJoinListener, this)
        server.pluginManager.registerEvents(PreventGetDebugItem, this)

        ////////////////////////////////////////////////
        // ^----------[ Register Weapons ]----------^ //
        WeaponHandler.register(
            // Default Weapon
            TestWeapon,

            // PleahMaCaka
            // Noting stuff

            // DwarfAmil
            Kight, // IRON_SWORD
            Rapier, // FLINT
            Bard, // GOLDEN_SWORD
            Explosioner, // GUNPOWDER

            // tmvkrpxl0
            HomingTurret // IRON_INGOT
        )

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