package com.james.fingerpaint.domain;

public class Node {
	private static final float TOLERANCE = 10;
	private float x;
	private float y;

	public Node(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public boolean matches(float x, float y) {
		return between(this.x, x - TOLERANCE, x + TOLERANCE) && between(this.y, y - TOLERANCE, y + TOLERANCE);
	}

	private boolean between(float f, float min, float max) {
		return f >= min && f <= max;
	}
}