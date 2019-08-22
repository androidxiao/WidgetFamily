package com.commonutils.kotyox.net.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by wei.
 * Date: 2019-04-24 22:04
 * Description:
 */
public class DateSerializer implements JsonSerializer<Date> {
    public DateSerializer() {
    }

    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getTime());
    }
}
