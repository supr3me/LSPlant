package com.kami.myapphooker;

import android.util.Log;

import org.lsposed.lsplant.Hooker;

import java.lang.reflect.InvocationTargetException;

/**
 * @author tong.
 * @date 2022/5/12
 */
public class ActivityHooker {
    public void onStop(Hooker.MethodCallback callback){
        MainActivity mainActivity = (MainActivity) callback.args[0];
        try {
            Log.d("ActivityHooker", "hook开始");
            callback.backup.invoke(mainActivity);
            Log.d("ActivityHooker", "hook结束");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
