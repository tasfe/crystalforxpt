package com.raisepartner.chartfusion.api.mscolumn3d;

public class MSColumn3DDataset extends com.raisepartner.chartfusion.api.Dataset {

	public MSColumn3DDataset() {
	}

	/**
	 * @see com.raisepartner.chartfusion.api.mscolumn3d.MSColumn3DSet
	 */
	public MSColumn3DSet createSetNode() {
		MSColumn3DSet node = new MSColumn3DSet();
		addNode(node);
		return node;
	}

}
