package com.kami.myapphooker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        try {
//            val method = this.javaClass.getDeclaredMethod("onStop")
//            val replace = ActivityHooker::class.java.getDeclaredMethod(
//                "onStop",
//                Hooker.MethodCallback::class.java
//            )
//            Hooker.hook(method, replace, ActivityHooker())
//            Log.d("MainActivity", "设置hook结束")
//        } catch (e: NoSuchMethodException) {
//            e.printStackTrace()
//        }
        test()
    }

    private fun test() {
        HookUtil.hook(this.javaClass.getDeclaredMethod("onStop")) {
            Log.d("HookUtil", "HookUtil hooks开始")
            HookUtil.invokeOriginal(it)
            Log.d("HookUtil", "HookUtil hooks结束")
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("mainActivity", " stop 了")
    }
}