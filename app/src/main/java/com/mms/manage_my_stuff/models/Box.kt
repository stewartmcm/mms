package com.mms.manage_my_stuff.models

class Box(var id: Int?,
          var moveId: Int?,
          var type: String?,
          var roomType: String?,
          var items: List<Item>? = null,
          var isPrioritized: Boolean = false,
          var isDelivered: Boolean = false)
