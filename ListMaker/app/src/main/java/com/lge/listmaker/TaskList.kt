package com.lge.listmaker

import android.os.Parcel
import android.os.Parcelable
import android.os.WorkSource

class TaskList(val name: String,val tasks: ArrayList<String> =  ArrayList<String>()) : Parcelable{


    constructor(source: Parcel) : this (source.readString(), source.createStringArrayList())

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {

        dest.writeString(name)
        dest.writeStringList(tasks)

    }

    companion object CREATOR : Parcelable.Creator<TaskList> {
        override fun createFromParcel(parcel: Parcel): TaskList {
            return TaskList(parcel)
        }

        override fun newArray(size: Int): Array<TaskList?> {
            return arrayOfNulls(size)
        }
    }




}