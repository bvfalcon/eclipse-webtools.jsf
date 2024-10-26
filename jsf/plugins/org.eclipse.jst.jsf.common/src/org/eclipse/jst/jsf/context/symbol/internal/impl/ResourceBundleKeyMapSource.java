package org.eclipse.jst.jsf.context.symbol.internal.impl;

import java.util.HashMap;
import java.util.Map;

public class ResourceBundleKeyMapSource extends HashMap {

	private Map owner;
	public ResourceBundleKeyMapSource(Map owner) {
		this.owner = owner;
	}

	public Map getOwner() {
		return owner;
	}
}