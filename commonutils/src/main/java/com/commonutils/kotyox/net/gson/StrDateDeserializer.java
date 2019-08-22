package com.commonutils.kotyox.net.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wei.
 * Date: 2019-04-24 22:05
 * Description:
 */
public class StrDateDeserializer implements JsonDeserializer<Date> {
    public StrDateDeserializer() {
    }

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = json.getAsJsonPrimitive().getAsString();

        try {
            return format.parse(date);
        } catch (ParseException var7) {
            return null;
        }
    }
}
