package com.james.fingerpaint.domain;

import java.util.ArrayList;
import java.util.List;

public class Nodes {
	private final List<Node> nodes;

	public Nodes() {
		nodes = new ArrayList<Node>();
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public Node matches(float x, float y) {
		for (Node node : nodes) {
			if (node.matches(x, y)) {
				return node;
			}
		}
		return null;
	}

	public void add(Node last) {
		this.nodes.add(last);
	}
}
