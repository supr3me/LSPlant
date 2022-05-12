package com.kami.myapphooker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.lsposed.lsplant.Hooker;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Method method = this.getClass().getDeclaredMethod("onStop");
            Method replace = ActivityHooker.class.getDeclaredMethod("onStop", Hooker.MethodCallback.class);
            Hooker.hook(method, replace, new ActivityHooker());
            Log.d("MainActivity", "设置hook结束");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("mainActivity"," stop 了");
    }
}