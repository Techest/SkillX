package skillx.core.handlers

import skillx.core.interfaces.IWeapon
import org.bukkit.plugin.java.JavaPlugin
import skillx.SkillX
import java.util.logging.Level

object WeaponHandler {

    val weapons: HashMap<String, IWeapon> = HashMap()

    /**
     * @param mainClass JavaPlugin
     * @sample code `WeaponHandler.initWeapons(this)` on extended JavaPlugin
     */
    fun initWeapons(mainClass: JavaPlugin) {
        weapons.values.forEach { weapon ->
            mainClass.server.pluginManager.registerEvents(weapon, mainClass)
        }
    }

    /**
     * @param elements
     *        Array : *Array
     *        else: register(weapon1, weapon2)
     * else return true!
     */
    fun register(vararg elements: IWeapon) {
        elements.forEach { wea: IWeapon ->
            if (weapons[wea.name] == null)
                weapons[wea.name] = wea
            else
                SkillX.instance.logger.log(Level.SEVERE, "Cannot load ${wea.name}, already registed.")
        }

    }

    /**
     * @param weapon IWeapon | String (name)
     */
    inline fun <reified T> remove(weapon: T) {
        if (weapon is String)
            weapons.remove(weapon)
        if (weapon is IWeapon && weapons[weapon.name] != null)
            weapons.remove(weapon.name)
    }

}