package com.ddang_.inyaksimulator.managers

import com.ddang_.inyaksimulator.objects.Member
import org.bukkit.entity.Player

class MemberManager {
    companion object {
        val memberList = arrayListOf<Member>()

        fun getMember(name: String) = memberList.find { it.name == name }

        fun set(p: Player) {
            Member(
                p.name,
                -1,
                null
            )
        }

        fun save(p: Player) {

        }
    }
}