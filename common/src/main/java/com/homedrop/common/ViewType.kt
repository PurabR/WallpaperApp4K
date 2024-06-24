package com.homedrop.common

import androidx.annotation.IntDef

const val ITEM_NONE = 0
const val ITEM_CATEGORIES= 1
const val ITEM_BOM= 2
const val ITEM_IMAGE = 3
const val ITEM_TITLE_BOM = 4
const val ITEM_CAT_DATA = 5

@IntDef(
    ITEM_NONE,
    ITEM_CATEGORIES,
    ITEM_BOM,
    ITEM_IMAGE,
    ITEM_TITLE_BOM,
    ITEM_CAT_DATA

)
@Retention(AnnotationRetention.SOURCE)
annotation class ViewType