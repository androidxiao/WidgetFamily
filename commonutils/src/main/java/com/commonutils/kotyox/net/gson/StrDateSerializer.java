package com.commonutils.kotyox.net.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wei.
 * Date: 2019-04-24 22:06
 * Description:
 */
public class StrDateSerializer implements JsonSerializer<Date> {
    public StrDateSerializer() {
    }

    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(src);
        return new JsonPrimitive(date);
    }
}
