package kg.bakai.repo.utils

import android.content.Context

interface TranslatableException {
    fun getDefaultResourceId(): Int
    fun getTranslatableMessage(context: Context): String = context.getString(getDefaultResourceId())
}