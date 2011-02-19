package com.james.fingerpaint;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

public class CustomExceptionHandler implements UncaughtExceptionHandler {
	private UncaughtExceptionHandler defaultUEH;
	private String localPath;

	/*
	 * if any of the parameters is null, the respective functionality will not
	 * be used
	 */
	public CustomExceptionHandler(String localPath) {
		this.localPath = localPath;
		this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
	}

	public void uncaughtException(Thread t, Throwable e) {
		String timestamp = String.valueOf(System.currentTimeMillis());
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		e.printStackTrace(printWriter);
		String stacktrace = result.toString();
		printWriter.close();
		String filename = timestamp + ".stacktrace";

		if (localPath != null) {
			writeToFile(stacktrace, filename);
		}
		defaultUEH.uncaughtException(t, e);
	}

	private void writeToFile(String stacktrace, String filename) {
		try {
			BufferedWriter bos = new BufferedWriter(new FileWriter(localPath + "/" + filename));
			bos.write(stacktrace);
			bos.flush();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}