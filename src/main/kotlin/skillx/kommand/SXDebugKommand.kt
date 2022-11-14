package skillx.kommand

import io.github.monun.kommand.PluginKommand
import skillx.SkillX
import skillx.core.WeaponHandler
import skillx.events.userBoard

object SXDebugKommand {

    private lateinit var plugin: SkillX

    internal fun register(plugin: SkillX, kommand: PluginKommand) {
        SXDebugKommand.plugin = plugin
        SkillX.instance.logger.info(":: skdebug registered.")
        kommand.register("skdebug") {

            then("select") {
                executes {
                    player.sendMessage("Open Debug Inventory")
                    player.openInventory(WeaponHandler.weaponsInv)
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


