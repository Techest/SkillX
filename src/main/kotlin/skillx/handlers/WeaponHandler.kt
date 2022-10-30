package skillx.handlers

import IWeapon
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
     * @see Map is not allow duplicate name;
     *      but checking to manually for handling
     * @return if failed to register, return false
     * @param T IWeapon | Array<IWeapon>
     * else return true!
     */
    inline fun <reified T> register(t: T): Boolean {
        // not an array
        if (t is IWeapon) {
            if (weapons[t.name] == null)
                weapons[t.name] = t
            return true
        }

        // array
        if (t is Array<*> && t.isArrayOf<IWeapon>()) {
            t.forEach { wea ->
                if (wea !is IWeapon) return false
                if (weapons[wea.name] == null)
                    weapons[wea.name] = wea
                return true
            }
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