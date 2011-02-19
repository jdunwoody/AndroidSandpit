//package com.james;
//
//import java.io.IOException;
//
//import android.app.Activity;
//import android.hardware.Camera;
//import android.hardware.Camera.Parameters;
//import android.hardware.Camera.PictureCallback;
//import android.hardware.Camera.ShutterCallback;
//import android.os.Bundle;
//import android.view.SurfaceHolder;
//
//public class TakePicture extends Activity {
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		// super.onCreate(savedInstanceState);
//		// setContentView(R.layout.main);
//
//		Camera camera = getCamera();
//		configureCamera(camera);
//
//		// Important: Pass a fully initialized SurfaceHolder to
//		// setPreviewDisplay(SurfaceHolder). Without a surface, the camera will
//		// be unable to start the preview.
//		try {
//			SurfaceHolder surfaceHolder = SurfaceHolder.getHolder();
//			camera.setPreviewDisplay(surfaceHolder);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Important: Call startPreview() to start updating the preview surface.
//		// Preview must be started before you can take a picture.
//		camera.startPreview();
//
//		// When you want, call takePicture(Camera.ShutterCallback,
//		// Camera.PictureCallback, Camera.PictureCallback,
//		// Camera.PictureCallback) to capture a photo. Wait for the callbacks to
//		// provide the actual image data.
//		PictureTaker pictureTaker = new PictureTaker();
//
//		ShutterCallback shutter = pictureTaker;
//		PictureCallback raw = pictureTaker;
//		PictureCallback jpeg = pictureTaker;
//		camera.takePicture(shutter, raw, jpeg);
//
//		// After taking a picture, preview display will have stopped. To take
//		// more photos, call startPreview() again first.
//
//		// Call stopPreview() to stop updating the preview surface.
//		camera.stopPreview();
//
//		// Important: Call release() to release the camera for use by other
//		// applications. Applications should release the camera immediately in
//		// onPause() (and re-open() it in onResume()).
//		camera.release();
//
//		// recordVideo();
//	}
//
//	private void configureCamera(Camera camera) {
//		// Get existing (default) settings with getParameters().
//		Parameters cameraParameters = camera.getParameters();
//
//		// If necessary, modify the returned Camera.Parameters object and call
//		// setParameters(Camera.Parameters).
//		camera.setParameters(cameraParameters);
//
//		// If desired, call setDisplayOrientation(int).
//	}
//
//	private Camera getCamera() {
//		// Obtain an instance of Camera from open(int).
//		return Camera.open();
//	}
//
//	private void recordVideo() {
//		// To quickly switch to video recording mode, use these steps:
//
//		//
//
//		// Obtain and initialize a Camera and start preview as described above.
//
//		// Call unlock() to allow the media process to access the camera.
//
//		// Pass the camera to setCamera(Camera). See MediaRecorder information
//		// about video recording.
//
//		// When finished recording, call reconnect() to re-acquire and re-lock
//		// the camera.
//
//		// If desired, restart preview and take more photos or videos.
//
//		// Call stopPreview() and release() as described above.
//
//	}
//
// }