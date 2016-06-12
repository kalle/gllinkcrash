package com.example.gllinkcrash;

import android.opengl.EGL14;
import android.opengl.GLES20;
import android.util.Log;

import com.android.grafika.gles.EglCore;

/**
 * Created by kalle on 2016-06-10.
 */
public class OffscreenThread implements Runnable {

    private static final String TAG = "OffscreenThread";
    private final long sleepTime = 5 * 1000;

    @Override
    public void run() {

        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "Initiating off screen surface");
        int maxSize = 1024;
        EglCore eglCore = new EglCore();

        android.opengl.EGLSurface surface = eglCore.createOffscreenSurface(maxSize, maxSize);
        eglCore.makeCurrent(surface);

        Log.i(TAG, "EGL_CLIENT_APIS = " + eglCore.queryString(EGL14.EGL_CLIENT_APIS));
        Log.i(TAG, "EGL_VENDOR = " + eglCore.queryString(EGL14.EGL_VENDOR));
        Log.i(TAG, "EGL_VERSION = " + eglCore.queryString(EGL14.EGL_VERSION));
        Log.i(TAG, "EGL_EXTENSIONS = " + eglCore.queryString(EGL14.EGL_EXTENSIONS));
        Log.i(TAG, "GL_VENDOR = " + GLES20.glGetString(GLES20.GL_VENDOR));
        Log.i(TAG, "GL_VERSION = " + GLES20.glGetString(GLES20.GL_VERSION));
        Log.i(TAG, "GL_RENDERER = " + GLES20.glGetString(GLES20.GL_RENDERER));


        GLProgram.compile();

        Log.e(TAG, "Everything works...");

        eglCore.makeNothingCurrent();
        eglCore.release();
    }

}
