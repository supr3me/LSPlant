package org.lsposed.lsplant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class Hooker {
    static {
        System.loadLibrary("lsplantHook");
    }

    public static class MethodCallback {
        public Method backup;
        public Object[] args;

        MethodCallback(Method backup, Object[] args) {
            this.backup = backup;
            this.args = args;
        }
    }

    public Method backup;

    private Member target;
    private Method replacement;
    private Object owner = null;

    private Hooker() {
    }
    native static boolean initHooker();

    private native Method doHook(Member original, Method callback);

    private native boolean doUnhook(Member target);

    public Object callback(Object[] args) throws InvocationTargetException, IllegalAccessException {
        var methodCallback = new MethodCallback(backup, args);
        return replacement.invoke(owner, methodCallback);
    }

    public boolean unhook() {
        return doUnhook(target);
    }

    public static Hooker hook(Member target, Method replacement, Object owner) {
        Hooker hooker = new Hooker();
        try {
            var callbackMethod = Hooker.class.getDeclaredMethod("callback", Object[].class);
            var result = hooker.doHook(target, callbackMethod);
            if (result == null) {
                return null;
            }
            hooker.backup = result;
            hooker.target = target;
            hooker.replacement = replacement;
            hooker.owner = owner;
        } catch (NoSuchMethodException ignored) {
        }
        return hooker;
    }
}
