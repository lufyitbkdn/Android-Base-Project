package com.shapee.sample.utils

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LocaleManager {
    fun updateContext(c: Context): Context? {
        return setNewLocale(c, getLanguage())
    }

    private fun setNewLocale(
        c: Context,
        language: String
    ): Context? {
        return updateResources(c, language)
    }

    private fun getLanguage(): String {
        return "en"
    }

    private fun updateResources(
        context: Context,
        language: String
    ): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}