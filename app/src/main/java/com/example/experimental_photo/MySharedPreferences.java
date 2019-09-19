package com.example.experimental_photo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/3/22 0022.
 * 保存一些简单的数据
 */

public class MySharedPreferences {

    //创建一个SharedPreferences    类似于创建一个数据库，库名为 data
    //（根据name查找SharedPreferences，若已经存在则获取，若不存在则创建一个新的）
    private static SharedPreferences share(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("date", Context.MODE_PRIVATE);
        return sharedPreferences;
    }


    //a的值
    public static String geta(Context context){
        return share(context).getString("a",null);
    }
    public static void seta(String a, Context context){
        SharedPreferences.Editor e = share(context).edit();
        e.putString("a",a);
        e.apply();
    }

    //b的值
    public static String getb(Context context){
        return share(context).getString("b",null);
    }
    public static void setb(String b, Context context){
        SharedPreferences.Editor e = share(context).edit();
        e.putString("b",b);
        e.apply();
    }

    //y的值
    public static int gety(Context context){
        return share(context).getInt("y",0);
    }
    public static void sety(int y, Context context){
        SharedPreferences.Editor e = share(context).edit();
        e.putInt("y",y);
        e.apply();
    }

}
