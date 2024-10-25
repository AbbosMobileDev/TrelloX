package com.abisoft.trellox.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class Task(
    @SerializedName("task_id")
    val taskId: Int,
    @SerializedName("index")
    val index: Int,
    @SerializedName("project_id")
    val projectId: String,
    @SerializedName("project_name")
    val projectName:String,
    @SerializedName("owner_id")
    val ownerId: Int,
    @SerializedName("owner_name")
    val ownerName: String,
    @SerializedName("owner_avatar")
    val ownerAvatar: String,
    @SerializedName("executor_id")
    val executorId: Int,
    @SerializedName("executor_name")
    val executorName: String,
    @SerializedName("executor_avatar")
    val executorAvatar: String,
    @SerializedName("task_date")
    val taskData: String,
    @SerializedName("term_date")
    val termData: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("priority")
    val priority: String,
    @SerializedName("status")
    val status: String
)