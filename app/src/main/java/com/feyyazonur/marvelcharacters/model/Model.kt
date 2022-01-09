package com.feyyazonur.marvelcharacters.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Model(
    @SerializedName("code") var code: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("copyright") var copyright: String? = null,
    @SerializedName("attributionText") var attributionText: String? = null,
    @SerializedName("attributionHTML") var attributionHTML: String? = null,
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("etag") var etag: String? = null
)

data class Data(
    @SerializedName("offset") var offset: String? = null,
    @SerializedName("limit") var limit: String? = null,
    @SerializedName("total") var total: String? = null,
    @SerializedName("count") var count: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()
)

data class Results(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("modified") var modified: String? = null,
    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("urls") var urls: ArrayList<Urls> = arrayListOf(),
    @SerializedName("thumbnail") var thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("comics") var comics: Comics? = Comics(),
    @SerializedName("stories") var stories: Stories? = Stories(),
    @SerializedName("events") var events: Events? = Events(),
    @SerializedName("series") var series: Series? = Series()
)

data class Urls(
    @SerializedName("type") var type: String? = null,
    @SerializedName("url") var url: String? = null
)

data class Thumbnail(
    @SerializedName("path") var path: String? = null,
    @SerializedName("extension") var extension: String? = null
)

data class Items(
    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String = ""
)

data class Comics(
    @SerializedName("available") var available: String? = null,
    @SerializedName("returned") var returned: String? = null,
    @SerializedName("collectionURI") var collectionURI: String? = null,
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()
)

data class Stories(
    @SerializedName("available") var available: String? = null,
    @SerializedName("returned") var returned: String? = null,
    @SerializedName("collectionURI") var collectionURI: String? = null,
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()
)

data class Events(
    @SerializedName("available") var available: String? = null,
    @SerializedName("returned") var returned: String? = null,
    @SerializedName("collectionURI") var collectionURI: String? = null,
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()
)

data class Series(
    @SerializedName("available") var available: String? = null,
    @SerializedName("returned") var returned: String? = null,
    @SerializedName("collectionURI") var collectionURI: String? = null,
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()
)

