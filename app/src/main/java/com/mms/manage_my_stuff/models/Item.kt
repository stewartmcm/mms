package com.mms.manage_my_stuff.models

class Item {

    var id: Int? = null
    var moveId: Int? = null
    var title: String? = null
    var isInBox: Boolean = false
    var isPrioritized: Boolean = false
    var isFragile: Boolean = false

    private constructor() {}

    constructor(id: Int, moveId: Int, title: String,  isInBox: Boolean, isPrioritized: Boolean, isFragile: Boolean) {
        this.id = id
        this.moveId = moveId
        this.title = title
        this.isInBox
        this.isPrioritized = isPrioritized
        this.isFragile = isFragile
    }
}
