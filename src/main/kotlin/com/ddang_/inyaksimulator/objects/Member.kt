package com.ddang_.inyaksimulator.objects

import com.ddang_.inyaksimulator.managers.MemberManager
import org.bukkit.Location

class Member (

    val name: String,
    var spawnCount: Int,
    var recallLocation: Location?,

    var loreStat: LoreStat

    ) {
    init {
        MemberManager.memberList.add(this)
    }
}