package com.app.v1.data

import com.app.v1.common.Constants

class MainItemDto <T> constructor(
    open var id: Int = -1
){
    var type_item = 0;
    var list = mutableListOf<T>()


}