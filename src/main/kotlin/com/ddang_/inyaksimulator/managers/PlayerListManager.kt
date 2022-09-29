package com.ddang_.inyaksimulator.managers

import com.ddang_.inyaksimulator.Inyaksimulator
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

class PlayerListManager {
    companion object {
        fun set(p: Player) {

            val header = mutableListOf<BaseComponent>()
            val headerFromConfig = Inyaksimulator.instance.config.getString("Message.playerList.top")
                .replace("%playerName%", p.name)
            val splitHeaderFC = headerFromConfig.split("%줄바꿈%")
            for (splitLine in splitHeaderFC) {
                header.add(TextComponent(splitLine))
            }

            val footer = mutableListOf<BaseComponent>()
            val footerFromConfig = Inyaksimulator.instance.config.getString("Message.playerList.bottom")
                .replace("%playerName%", p.name)
            val splitFooterFC = footerFromConfig.split("%줄바꿈%")
            for (splitLine in splitFooterFC) {
                footer.add(TextComponent(splitLine))
            }

            p.setPlayerListHeaderFooter(
                header.toTypedArray(),
                footer.toTypedArray()
            )
        }
    }
}