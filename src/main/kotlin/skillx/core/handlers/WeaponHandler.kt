package skillx.core.handlers

import skillx.core.interfaces.IWeapon
import org.bukkit.plugin.java.JavaPlugin

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
    fun register(vararg elements: IWeapon): Boolean {
        elements.forEach { wea: IWeapon ->
            if (weapons[wea.name] == null)
                weapons[wea.name] = wea
            return true
        }
        return false
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