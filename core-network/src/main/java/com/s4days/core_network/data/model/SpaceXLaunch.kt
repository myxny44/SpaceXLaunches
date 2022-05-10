package com.s4days.core_network.data.model

import com.google.gson.annotations.SerializedName

data class SpaceXLaunch(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("fairings")
    var fairings: Fairings? = Fairings(),
    @SerializedName("links")
    var links: Links? = Links(),
    @SerializedName("static_fire_date_utc")
    var staticFireDateUtc: String? = null,
    @SerializedName("static_fire_date_unix")
    var staticFireDateUnix: Int? = null,
    @SerializedName("net")
    var net: Boolean? = null,
    @SerializedName("window")
    var window: Int? = null,
    @SerializedName("rocket")
    var rocket: String? = null,
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("failures")
    var failures: ArrayList<Failures> = arrayListOf(),
    @SerializedName("details")
    var details: String? = null,
    @SerializedName("crew")
    var crew: ArrayList<String> = arrayListOf(),
    @SerializedName("ships")
    var ships: ArrayList<String> = arrayListOf(),
    @SerializedName("capsules")
    var capsules: ArrayList<String> = arrayListOf(),
    @SerializedName("payloads")
    var payloads: ArrayList<String> = arrayListOf(),
    @SerializedName("launchpad")
    var launchpad: String? = null,
    @SerializedName("flight_number")
    var flightNumber: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("date_utc")
    var dateUtc: String? = null,
    @SerializedName("date_unix")
    var dateUnix: Long? = null,
    @SerializedName("date_local")
    var dateLocal: String? = null,
    @SerializedName("date_precision")
    var datePrecision: String? = null,
    @SerializedName("upcoming")
    var upcoming: Boolean? = null,
    @SerializedName("cores")
    var cores: ArrayList<Cores> = arrayListOf(),
    @SerializedName("auto_update")
    var autoUpdate: Boolean? = null,
    @SerializedName("tbd")
    var tbd: Boolean? = null,
    @SerializedName("launch_library_id")
    var launchLibraryId: String? = null
)

data class Fairings(

    @SerializedName("reused")
    var reused: Boolean? = null,
    @SerializedName("recovery_attempt")
    var recoveryAttempt: Boolean? = null,
    @SerializedName("recovered")
    var recovered: Boolean? = null,
    @SerializedName("ships")
    var ships: ArrayList<String> = arrayListOf()

)

data class Patch(

    @SerializedName("small")
    var small: String? = null,
    @SerializedName("large")
    var large: String? = null

)

data class Reddit(

    @SerializedName("campaign")
    var campaign: String? = null,
    @SerializedName("launch")
    var launch: String? = null,
    @SerializedName("media")
    var media: String? = null,
    @SerializedName("recovery")
    var recovery: String? = null

)

data class Flickr(

    @SerializedName("small")
    var small: ArrayList<String> = arrayListOf(),
    @SerializedName("original")
    var original: ArrayList<String> = arrayListOf()

)

data class Links(

    @SerializedName("patch")
    var patch: Patch? = Patch(),
    @SerializedName("reddit")
    var reddit: Reddit? = Reddit(),
    @SerializedName("flickr")
    var flickr: Flickr? = Flickr(),
    @SerializedName("presskit")
    var presskit: String? = null,
    @SerializedName("webcast")
    var webcast: String? = null,
    @SerializedName("youtube_id")
    var youtubeId: String? = null,
    @SerializedName("article")
    var article: String? = null,
    @SerializedName("wikipedia")
    var wikipedia: String? = null

)

data class Failures(

    @SerializedName("time")
    var time: Int? = null,
    @SerializedName("altitude")
    var altitude: String? = null,
    @SerializedName("reason")
    var reason: String? = null

)

data class Cores(

    @SerializedName("core")
    var core: String? = null,
    @SerializedName("flight")
    var flight: Int? = null,
    @SerializedName("gridfins")
    var gridfins: Boolean? = null,
    @SerializedName("legs")
    var legs: Boolean? = null,
    @SerializedName("reused")
    var reused: Boolean? = null,
    @SerializedName("landing_attempt")
    var landingAttempt: Boolean? = null,
    @SerializedName("landing_success")
    var landingSuccess: String? = null,
    @SerializedName("landing_type")
    var landingType: String? = null,
    @SerializedName("landpad")
    var landpad: String? = null

)