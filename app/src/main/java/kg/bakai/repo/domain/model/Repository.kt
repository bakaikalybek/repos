package kg.bakai.repo.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    val id: Int,
    val name: String,
    val owner: Owner,
    @SerializedName("stargazers_count")
    val starCount: Int,
    @SerializedName("open_issues_count")
    val openIssueCount: Int,
    var isFavorite: Boolean = false
): Parcelable
