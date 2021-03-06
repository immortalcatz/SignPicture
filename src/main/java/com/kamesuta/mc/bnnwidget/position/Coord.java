package com.kamesuta.mc.bnnwidget.position;

import com.kamesuta.mc.bnnwidget.motion.IMotion;
import com.kamesuta.mc.bnnwidget.motion.MotionQueue;

public class Coord {
	public final MotionQueue motion;
	public CoordSide side;
	public CoordType type;

	public Coord(final float coord, final CoordSide side, final CoordType type) {
		this.motion = new MotionQueue(coord);
		this.side = side;
		this.type = type;
	}

	public float get() {
		return this.motion.get();
	}

	public float getAbsCoord(final float abslength) {
		return this.type.calc(abslength, get());
	}

	public Coord add(final IMotion motion) {
		this.motion.add(motion);
		return this;
	}

	public Coord addAfter(final MotionQueue q) {
		this.motion.addAfter(q);
		return this;
	}

	public Coord start() {
		this.motion.start();
		return this;
	}

	@Override
	public String toString() {
		return String.format("Coord [coord=%s, side=%s, type=%s]", get(), this.side, this.type);
	}

	public static enum CoordSide {
		Top,
		Left,
		Bottom,
		Right,
		Width,
		Height,
	}

	public static enum CoordType {
		Absolute {
			@Override
			public float calc(final float all, final float c) {
				return c;
			}
		},
		Percent {
			@Override
			public float calc(final float all, final float c) {
				return all * c;
			}
		},
		;

		public abstract float calc(float all, float f);
	}

	public static Coord top(final float n) {
		return new Coord(n, CoordSide.Top, CoordType.Absolute);
	}

	public static Coord ptop(final float n) {
		return new Coord(n, CoordSide.Top, CoordType.Percent);
	}

	public static Coord left(final float n) {
		return new Coord(n, CoordSide.Left, CoordType.Absolute);
	}

	public static Coord pleft(final float n) {
		return new Coord(n, CoordSide.Left, CoordType.Percent);
	}

	public static Coord bottom(final float n) {
		return new Coord(n, CoordSide.Bottom, CoordType.Absolute);
	}

	public static Coord pbottom(final float n) {
		return new Coord(n, CoordSide.Bottom, CoordType.Percent);
	}

	public static Coord right(final float n) {
		return new Coord(n, CoordSide.Right, CoordType.Absolute);
	}

	public static Coord pright(final float n) {
		return new Coord(n, CoordSide.Right, CoordType.Percent);
	}

	public static Coord width(final float n) {
		return new Coord(n, CoordSide.Width, CoordType.Absolute);
	}

	public static Coord pwidth(final float n) {
		return new Coord(n, CoordSide.Width, CoordType.Percent);
	}

	public static Coord height(final float n) {
		return new Coord(n, CoordSide.Height, CoordType.Absolute);
	}

	public static Coord pheight(final float n) {
		return new Coord(n, CoordSide.Height, CoordType.Percent);
	}
}
