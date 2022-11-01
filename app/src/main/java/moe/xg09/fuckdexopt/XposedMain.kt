package moe.xg09.fuckdexopt

import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedMain : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        runCatching {
            XposedHelpers.findAndHookMethod(
                Class.forName(
                    "com.android.server.pm.dex.DexoptOptions",
                    false,
                    lpparam.classLoader
                ),
                "getCompilationReason",
                object : XC_MethodReplacement() {
                    override fun replaceHookedMethod(param: MethodHookParam?): Any {
                        return 1
                    }
                }
            )
        }.onFailure {
            Log.e("FuckDexopt", "hook failed", it)
        }
    }
}