package kg.bakai.repo.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    @SerializedName("login")
    val ownerName: String,
    @SerializedName("avatar_url")
    val avatar: String
): Parcelable