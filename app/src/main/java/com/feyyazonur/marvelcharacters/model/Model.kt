package com.feyyazonur.marvelcharacters.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Model(
    @SerializedName("code")
    var code: String?,

    @SerializedName("status")
    var status: String?,

    @SerializedName("copyright")
    var copyright: String?,

    @SerializedName("attributionText")
    var attributionText: String?,

    @SerializedName("attributionHTML")
    var attributionHTML: String?,

    @SerializedName("data")
    var data: Data?,

    @SerializedName("etag")
    var etag: String?
) : Parcelable

@Parcelize
data class Data(
    @SerializedName("offset")
    var offset: String?,

    @SerializedName("limit")
    var limit: String?,

    @SerializedName("total")
    var total: String?,

    @SerializedName("count")
    var count: String?,

    @SerializedName("results")
    var results: List<Results>?
) : Parcelable

@Parcelize
data class Results(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("modified")
    var modified: String?,

    @SerializedName("resourceURI")
    var resourceURI: String?,

    @SerializedName("urls")
    var urls: List<Urls>?,

    @SerializedName("thumbnail")
    var thumbnail: Thumbnail?,

    @SerializedName("comics")
    var comics: Comics?,

    @SerializedName("stories")
    var stories: Stories?,

    @SerializedName("events")
    var events: Events?,

    @SerializedName("series")
    var series: Series?
) : Parcelable

@Parcelize
data class Urls(
    @SerializedName("type")
    var type: String?,

    @SerializedName("url")
    var url: String?
) : Parcelable

@Parcelize
data class Thumbnail(
    @SerializedName("path")
    var path: String?,

    @SerializedName("extension")
    var extension: String?
) : Parcelable

@Parcelize
data class Items(
    @SerializedName("resourceURI")
    var resourceURI: String?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("type")
    var type: String? = ""
) : Parcelable

@Parcelize
data class Comics(
    @SerializedName("available")
    var available: String?,

    @SerializedName("returned")
    var returned: String?,

    @SerializedName("collectionURI")
    var collectionURI: String?,

    @SerializedName("items")
    var items: List<Items>?
) : Parcelable

@Parcelize
data class Stories(
    @SerializedName("available")
    var available: String?,

    @SerializedName("returned")
    var returned: String?,

    @SerializedName("collectionURI")
    var collectionURI: String?,

    @SerializedName("items")
    var items: List<Items>?
) : Parcelable

@Parcelize
data class Events(
    @SerializedName("available")
    var available: String?,

    @SerializedName("returned")
    var returned: String?,

    @SerializedName("collectionURI")
    var collectionURI: String?,

    @SerializedName("items")
    var items: List<Items>?
) : Parcelable

@Parcelize
data class Series(
    @SerializedName("available")
    var available: String?,

    @SerializedName("returned")
    var returned: String?,

    @SerializedName("collectionURI")
    var collectionURI: String?,

    @SerializedName("items")
    var items: List<Items>?
) : Parcelable