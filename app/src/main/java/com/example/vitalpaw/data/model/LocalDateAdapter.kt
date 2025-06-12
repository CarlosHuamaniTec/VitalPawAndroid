package com.example.vitalpaw.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateAdapter : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    @RequiresApi(Build.VERSION_CODES.O)
    override fun serialize(src: LocalDate?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.format(formatter))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(json: JsonElement?, typeOf: java.lang.reflect.Type?, context: JsonDeserializationContext?): LocalDate {
        return LocalDate.parse(json?.asString, formatter)
    }
}

object LocalDateTimeAdapter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @RequiresApi(Build.VERSION_CODES.O)
    override fun serialize(src: LocalDateTime?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.format(formatter))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(
        json: JsonElement?,
        typeOf: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime? {
        return json?.asString?.let { LocalDateTime.parse(it, formatter) }
    }
}