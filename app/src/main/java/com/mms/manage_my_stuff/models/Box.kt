package com.mms.manage_my_stuff.models

import java.util.*

class Box(var id: Int,
          var title: String,
          var boxes: ArrayList<PackedItem> = arrayListOf(),
          var size: Int,
          var isPrioritized: Boolean,
          var isDelivered: Boolean)
