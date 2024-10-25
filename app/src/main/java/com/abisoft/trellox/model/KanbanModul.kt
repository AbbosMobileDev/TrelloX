package com.abisoft.trellox.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class KanbanColumn(val title: String, val tasks: List<TaskRow>)
@Parcelize
data class TaskRow(
    val name: String,
    val taskId: Int,
    val index: Int,
    val projectId: String,
    val projectName: String,
    val ownerId: Int,
    val ownerName: String,
    val ownerAvatar: String,
    val executorId: Int,
    val executorName: String,
    val executorAvatar: String,
    val taskData: String,
    val termData: String,
    val priority: String,
    val status: String



    ) : Parcelable


