package com.kami.myapphooker

import org.lsposed.lsplant.Hooker
import java.lang.reflect.Method

/**
 * @author tong.
 * @date 2022/8/19
 */
object HookUtil {
    fun hook(target: Method, replaceCallback: ((Hooker.MethodCallback) -> Unit)) {
        try {
            val replace =
                IHookMethod::class.java.getDeclaredMethod("hook", Hooker.MethodCallback::class.java)
            Hooker.hook(target, replace, object : IHookMethod {
                override fun hook(callback: Hooker.MethodCallback) {
                    replaceCallback(callback)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 调用原来的方法
     */
    fun invokeOriginal(callback: Hooker.MethodCallback) {
        val original = callback.args[0]
        callback.backup.invoke(original)
    }

    /**
     * 接口来承载替换的方法
     */
    interface IHookMethod {
        fun hook(callback: Hooker.MethodCallback)
    }
}