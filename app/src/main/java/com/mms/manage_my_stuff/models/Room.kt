package com.mms.manage_my_stuff.models

import java.util.*

class Room {

    var id: Int? = null
    var moveId: Int? = null
    var roomType: String? = null
    var boxes: ArrayList<Box> = arrayListOf()
    var boxCount: Int = 0
    var isPackedUp: Boolean = false

    private constructor() {}

    constructor(id: Int, moveId: Int, roomType: String, boxes: ArrayList<Box>, boxCount: Int, isPackedUp: Boolean) {
        this.id = id
        this.moveId = moveId
        this.roomType = roomType
        this.boxes = boxes
        this.boxCount = boxCount
        this.isPackedUp = isPackedUp
    }
}
