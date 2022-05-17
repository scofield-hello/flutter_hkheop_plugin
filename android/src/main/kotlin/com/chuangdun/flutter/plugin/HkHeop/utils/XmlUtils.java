package com.chuangdun.flutter.plugin.HkHeop.utils;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Build;
import android.util.Log;
import android.util.Xml;

import androidx.annotation.RequiresApi;


public class XmlUtils<T> {
    /**
     * 解析XML转换成字符串数字
     *
     * @param is
     *      输入流
     * @return List<String>
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static  List<String> parse(InputStream is,
                                      List<String> fields) {
        Log.v("rss", "开始解析XML.");
        List<String> list = new ArrayList<>();
        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(is, "UTF-8");
            int event = xmlPullParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                if(event == XmlPullParser.START_TAG){
                    fields.forEach(s -> {
                        if (s.equals(xmlPullParser.getName())){
                            try {
                                list.add(xmlPullParser.nextText());
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (XmlPullParserException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                event = xmlPullParser.next();
            }
        } catch (Exception e) {
            Log.e("rss", "解析XML异常：" + e.getMessage());
            throw new RuntimeException("解析XML异常：" + e.getMessage());
        }
        return list;
    }
}