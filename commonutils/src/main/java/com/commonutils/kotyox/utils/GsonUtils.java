package com.commonutils.kotyox.utils;

import android.support.annotation.NonNull;

import com.commonutils.kotyox.net.gson.DateDeserializer;
import com.commonutils.kotyox.net.gson.DateSerializer;
import com.commonutils.kotyox.net.gson.StrDateDeserializer;
import com.commonutils.kotyox.net.gson.StrDateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by wei.
 * Date: 2019-04-24 22:03
 * Description:
 */
public class GsonUtils {

    private static GsonBuilder gsonBuilder = null;

    public GsonUtils() {
    }

    public static Gson getGson() {
        GsonBuilder gsonBuilder = getGsonBuilder();
        return gsonBuilder.create();
    }

    public static void initStrDate() {
        GsonBuilder gsonBuilder = getGsonBuilder();
        gsonBuilder.setDateFormat(1).registerTypeAdapter(Date.class, new StrDateSerializer()).registerTypeAdapter(Date.class, new StrDateDeserializer());
    }

    public static void initLongDate() {
        GsonBuilder gsonBuilder = getGsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeAdapter(Date.class, new DateSerializer()).registerTypeAdapter(Date.class, new DateDeserializer());
    }

    public static void registerTypeAdapter(Type type, Object typeAdapter) {
        GsonBuilder gsonBuilder = getGsonBuilder();
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
    }

    @NonNull
    private static GsonBuilder getGsonBuilder() {
        if (gsonBuilder == null) {
            gsonBuilder = new GsonBuilder();
        }

        return gsonBuilder;
    }
}
