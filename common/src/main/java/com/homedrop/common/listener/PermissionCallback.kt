package com.homedrop.common.listener

interface PermissionCallback {

    fun onGrant(permissions: Array<String>, requestCode: Int)

    fun onDeny(permissions: Array<String>, requestCode: Int)

}