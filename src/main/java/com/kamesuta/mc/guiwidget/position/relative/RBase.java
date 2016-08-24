package com.kamesuta.mc.guiwidget.position.relative;

public abstract class RBase implements RCommon {
	public boolean isAnchor;

	public RBase(final boolean isAnchor) {
		this.isAnchor = isAnchor;
	}

	public void setAnchor(final boolean isAnchor) {
		this.isAnchor = isAnchor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.isAnchor ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RBase))
			return false;
		final RBase other = (RBase) obj;
		if (this.isAnchor != other.isAnchor)
			return false;
		return true;
	}
}