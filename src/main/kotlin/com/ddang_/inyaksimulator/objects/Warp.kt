package com.ddang_.inyaksimulator.objects

import com.ddang_.inyaksimulator.managers.WarpManager
import org.bukkit.Location

class Warp (val name: String, var loc: Location) {
    init {
        WarpManager.warpList.add(this)
    }
}