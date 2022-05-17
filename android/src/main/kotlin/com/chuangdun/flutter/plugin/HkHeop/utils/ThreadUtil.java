package com.chuangdun.flutter.plugin.HkHeop.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程帮助类
 */
public final class ThreadUtil {

        private static ExecutorService sSingleThread = Executors.newSingleThreadExecutor();
        private static ExecutorService sThreadPool = Executors.newCachedThreadPool();

        private final static Handler MAIN = new Handler(Looper.getMainLooper());

        public static void postMain(Runnable runnable) {
                MAIN.post(runnable);
                }

        public static void postDelayed(final Runnable runnable, long delayMillis) {
                MAIN.postDelayed(runnable, delayMillis);
                }

        /**
         * 串行执行命令
         * @param runnable
         */
        public static void runTaskInSerial(Runnable runnable) {
            sSingleThread.submit(runnable);
        }

}
