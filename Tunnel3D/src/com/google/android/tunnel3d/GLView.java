package com.google.android.tunnel3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.opengl.GLU;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GLView extends SurfaceView implements SurfaceHolder.Callback {
    private OpenGLContext ctx;
    private Tunnel3D tunnel;
    private boolean created;
    private GL10 gl;
    private int w;
    private int h;
    private Bitmap bmp;
    private int tex;

    public GLView(Context context) {
        // Parent...
        super(context);
        getHolder().setCallback(this);

        // Internal members..
        ctx = new OpenGLContext(OpenGLContext.DEPTH_BUFFER);
        gl = (GL10) ctx.getGL();
        tunnel = new Tunnel3D(10, 20);
        created = false;

        // Enabling the state...
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // Loading texture...
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.plants03);
        tex = loadTexture(gl, bmp);
    }

    public boolean surfaceCreated(SurfaceHolder holder) {
        synchronized (this) {
            created = true;
        }
        return true;
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        synchronized (this) {
            created = false;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        synchronized (this) {
            this.w = w;
            this.h = h;
        }
    }

    public void render() {
        // Check the created flag...
        boolean c = false;
        synchronized (this) {
            c = created;
        }
        if (!c)
            return;

        // Start the surface holder...
        SurfaceHolder sh = getHolder();
        Canvas g = sh.lockCanvas();

        // Hooking GL with the view...
        ctx.makeCurrent(g, null);

        // Setting up the projection...
        float ratio = (float) w / h;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glViewport(0, 0, w, h);
        GLU.gluPerspective(gl, 45.0f, ((float) w) / h, 1f, 100f);

        // Setting up the modelview...
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        // Clear the z-buffer...
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // Render the tunnel...
        tunnel.render(gl, -1.6f);
        tunnel.nextFrame();

        // OpenGL finish
        gl.glFlush();
        gl.glFinish();

        // Finish with hook
        ctx.waitGL();

        // End the surface holder...
        sh.unlockCanvasAndPost(g);
    }

    private int loadTexture(GL10 gl, Bitmap bmp) {
        ByteBuffer bb = ByteBuffer.allocateDirect(bmp.height() * bmp.width() * 4);
        bb.order(ByteOrder.nativeOrder());
        IntBuffer ib = bb.asIntBuffer();

        for (int y = 0; y < bmp.height(); y++)
            for (int x = 0; x < bmp.width(); x++) {
                ib.put(bmp.getPixel(x, y));
            }
        ib.position(0);
        bb.position(0);

        int[] tmp_tex = new int[1];

        gl.glGenTextures(1, tmp_tex, 0);
        int tex = tmp_tex[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex);
        gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, GL10.GL_RGBA, bmp.width(), bmp.height(), 0, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, bb);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        return tex;
    }
}