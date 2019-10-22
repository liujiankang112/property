package com.goketech.smartcommunity.model.bean

data class LoginBean(
    val `data`: Data,
    val msg: String,
    val status: Int
)

data class Data(
    val avatar: String,
    val houses: List<House>,
    val id: Int,
    val identity: String,
    val is_app: Int,
    val is_bind_wx: Int,
    val is_open: Int,
    val is_smart: Int,
    val name: String,
    val nick: String,
    val phone: String,
    val remark: String,
    val sex: Int,
    val token: String
)

data class House(
    val bulid: String,
    val community: String,
    val community_id: Int,
    val company_id: Int,
    val current: Int,
    val floor: String,
    val house_id: Int,
    val is_approve: Int,
    val is_owner: Int,
    val phone: String,
    val principal: String,
    val room: String,
    val unit: String
)