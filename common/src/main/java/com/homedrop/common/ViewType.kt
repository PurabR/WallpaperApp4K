package com.homedrop.common

import androidx.annotation.IntDef

const val ITEM_NONE = 0
const val ITEM_CATEGORIES= 1
const val ITEM_BOM= 2
const val ITEM_IMAGE = 3
const val ITEM_TITLE = 4
const val ITEM_SPECIFIC_CATEGORIES = 5
const val ITEM_SET_WALLPAPER =6
const val ITEM_DOWNLOAD = 7

@IntDef(
    ITEM_NONE,
    ITEM_CATEGORIES,
    ITEM_BOM,
    ITEM_IMAGE,
    ITEM_TITLE,
    ITEM_SPECIFIC_CATEGORIES,
    ITEM_SET_WALLPAPER,
    ITEM_DOWNLOAD

)
@Retention(AnnotationRetention.SOURCE)
annotation class ViewType