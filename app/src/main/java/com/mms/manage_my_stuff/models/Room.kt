package com.mms.manage_my_stuff.models

import java.util.*

class Room {

    var id: Int = 0
    var title: String? = null
    var boxes: ArrayList<Box> = arrayListOf()
    var boxCount: Int = 0
    var isPackedUp: Boolean = false

    private constructor() {}

    constructor(id: Int, title: String, boxes: ArrayList<Box>, boxCount: Int, isPackedUp: Boolean) {
        this.id = id
        this.title = title
        this.boxes = boxes
        this.boxCount = boxCount
        this.isPackedUp = isPackedUp
    }

}
