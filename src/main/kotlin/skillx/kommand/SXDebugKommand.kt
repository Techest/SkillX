package skillx.kommand.debug

import io.github.monun.kommand.PluginKommand
import skillx.SkillX
import skillx.events.userBoard
import skillx.inventory.skDebugInventory

object SXDebugKommand {

    private lateinit var plugin: SkillX

    internal fun register(plugin: SkillX, kommand: PluginKommand) {
        this.plugin = plugin

        kommand.register("skdebug") {

            then("select") {
                executes {
                    player.sendMessage("Open Debug Inventory")
                    player.openInventory(skDebugInventory)
                }
            }

            then("board") {
                then("refresh") {
                    executes {
                        player.scoreboard = userBoard
                        player.sendMessage("refresh scoreboard")
                    }
                }
            }

        }
    }

}


