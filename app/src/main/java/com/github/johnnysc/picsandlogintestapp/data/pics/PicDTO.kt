package com.github.johnnysc.picsandlogintestapp.data.pics

import com.google.gson.annotations.SerializedName

/**
 * Данные по изображениям от сервера
 *
 * @author Asatryan on 05.04.21
 **/
data class PicDTO(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("url") val url: String,
    @SerializedName("download_url") val downloadUrl: String
)