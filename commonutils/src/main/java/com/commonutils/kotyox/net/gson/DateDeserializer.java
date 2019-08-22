package com.commonutils.kotyox.net.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by wei.
 * Date: 2019-04-24 22:05
 * Description:
 */
public class DateDeserializer implements JsonDeserializer<Date> {
    public DateDeserializer() {
    }

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Date(json.getAsJsonPrimitive().getAsLong());
    }
}
