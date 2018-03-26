package com.kotlin.base.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import org.jetbrains.anko.activityManager
import java.util.*

/**
 * Created by jiangyuanyuan on 26/3/18.
 */
class AppManager private constructor(){
    private val activityStack:Stack<Activity> = Stack()

    companion object {
        val instance:AppManager by lazy { AppManager() }
    }
    /*
        Activity入栈
     */
    fun addActivity(activity:Activity){
        activityStack.add(activity)
    }
    /*
        Activity出栈
     */
    fun removeActivity(activity:Activity){
        activity.finish()
        activityStack.remove(activity)
    }
    /*
        获取当前栈顶
     */
    fun currentActivity():Activity{
        return activityStack.lastElement()
    }
    /*
        清理栈
     */

    fun finishAllActivitys(){
        for (activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    /*
        退出应用程序
     */

    @SuppressLint("MissingPermission")
    fun exitApp(context:Context){
        finishAllActivitys()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }

}