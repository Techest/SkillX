package skillx.core

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

interface IWeapon : Listener {

    /**
     *  Duplicate names are not allowed
     */
    val name: String

    /**
    * It can be nullable but recommended
     */
    val description: String?

    /**
     * TODO bind to spacific item
     */
    val item: Material

    @EventHandler
    fun action(event: PlayerInteractEvent) {
        if (event.item?.type != item) return

        val player = event.player
        val action = event.action

        if (
            player.isSneaking &&
            (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)
        ) return onShiftLeftClick(event)

        if (
            player.isSneaking &&
            (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
        ) return onShiftRightClick(event)

        when (action) {
            Action.LEFT_CLICK_BLOCK, Action.LEFT_CLICK_AIR ->
                onLeftClick(event)

            Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR ->
                onRightClick(event)

            else -> return
        }

        when (action) {
            Action.LEFT_CLICK_BLOCK -> return onLeftClickBlock(event)

            Action.RIGHT_CLICK_BLOCK -> return onRightClickBlock(event)

            Action.LEFT_CLICK_AIR -> return onLeftClickAir(event)

            Action.RIGHT_CLICK_AIR -> return onRightClickAir(event)

            else -> return
        }
    }

    fun onLeftClick(event: PlayerInteractEvent) {}
    fun onRightClick(event: PlayerInteractEvent) {}

    fun onLeftClickBlock(event: PlayerInteractEvent) {}
    fun onRightClickBlock(event: PlayerInteractEvent) {}

    fun onLeftClickAir(event: PlayerInteractEvent) {}
    fun onRightClickAir(event: PlayerInteractEvent) {}

    fun onShiftLeftClick(event: PlayerInteractEvent) {}
    fun onShiftRightClick(event: PlayerInteractEvent) {}

}