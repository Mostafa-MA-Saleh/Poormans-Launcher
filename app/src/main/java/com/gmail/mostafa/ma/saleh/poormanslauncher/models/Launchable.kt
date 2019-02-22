package com.gmail.mostafa.ma.saleh.poormanslauncher.models

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Launchables")
data class Launchable(
    @PrimaryKey var packageName: String,
    @ColumnInfo var label: String
) : Comparable<Launchable> {

    @get:Ignore
    val icon: String
        get() = "$packageName.png"

    fun launch(context: Context) = context.packageManager.getLaunchIntentForPackage(packageName)?.run {
        context.startActivity(this)
    } ?: Unit

    infix fun hasSameContentAs(launchable: Launchable) =
        label == launchable.label && packageName == launchable.packageName

    override fun equals(other: Any?) = (other as? Launchable)?.packageName == packageName

    override fun hashCode() = packageName.hashCode()

    override fun compareTo(other: Launchable) = label.compareTo(other.label)
}