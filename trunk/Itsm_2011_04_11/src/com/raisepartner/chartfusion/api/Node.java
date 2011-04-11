package com.raisepartner.chartfusion.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

/**
 * Represents and xml node.
 */
public class Node {

	/**
	 * The node name
	 */
	public String name;

	/**
	 * The list of subnodes
	 */
	public final List<Node> subnodes = new ArrayList<Node>();

	/**
	 * The attributes of the node.
	 */
	public final Properties attributes = new Properties();

	/**
	 * Builds a node
	 * 
	 * @param name
	 *            is the name of the node
	 */
	public Node(String name) {
		this.name = name;
	}

	/**
	 * Add a subnode.
	 * 
	 * @param n
	 *            is the new subnode
	 */
	public void addNode(Node n) {
		subnodes.add(n);
	}

	/**
	 * Defines an attribute. If the value is null, the attribute is removed.
	 * 
	 * @param name
	 *            is the attribute name
	 * @param value
	 *            is the attribute value
	 */
	public void setAttribute(String name, String value) {
		if (value == null) {
			attributes.remove(name);
		} else {
			attributes.setProperty(name, value);
		}
	}

	/**
	 * Builds the XML representation of the node.
	 */
	public String toString() {
		return toString(new StringBuffer(), "").toString();
	}

	/**
	 * Recursives method for building XML representation of the node
	 * 
	 * @param sb
	 *            is the StringBuffer to fill with the representation of the
	 *            current node. Must not be null.
	 * @param tab
	 *            is the tabulation prefix to insert at each line begin. Must
	 *            not be null.
	 * @return the tab parameter.
	 */
	private StringBuffer toString(StringBuffer sb, String tab) {
		final String innerTab = tab + "";
		sb.append(tab).append("<").append(name);
		for (Entry<Object, Object> me : attributes.entrySet()) {
			if (me.getValue() != null) {
				sb.append(" ").append((String) me.getKey());
				sb.append("='").append((String) me.getValue()).append("'");
			}
		}
		if (attributes.size() == 0) {
			sb.append(" ");
		}
		if (subnodes.isEmpty()) {
			sb.append("/>");
		} else {
			sb.append(">");
			for (Object element : subnodes) {
				Node n = (Node) element;
				n.toString(sb, innerTab);
			}
			sb.append(tab).append("</").append(name).append(">");
		}
		return sb;
	}
}
