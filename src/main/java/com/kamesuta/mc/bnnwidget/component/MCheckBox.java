package com.kamesuta.mc.bnnwidget.component;

import static org.lwjgl.opengl.GL11.*;

import com.kamesuta.mc.bnnwidget.WEvent;
import com.kamesuta.mc.bnnwidget.position.Area;
import com.kamesuta.mc.bnnwidget.position.Point;
import com.kamesuta.mc.bnnwidget.position.R;
import com.kamesuta.mc.signpic.render.RenderHelper;

public class MCheckBox extends MLabel {
	protected boolean checked = true;

	public MCheckBox(final R position, final String text) {
		super(position, text);
	}

	public void check(final boolean check) {
		this.checked = check;
		onCheckChanged(!check);
	}

	public final boolean isCheck() {
		return this.checked;
	}

	protected void onCheckChanged(final boolean oldCheck) {
	}

	@Override
	public void mouseClicked(final WEvent ev, final Area pgp, final Point p, final int button) {
		final Area a = getGuiPosition(pgp);
		if (a.pointInside(p))
			check(!this.checked);
	}

	@Override
	public void draw(final WEvent ev, final Area pgp, final Point p, final float frame) {
		final Area o = getGuiPosition(pgp);
		final Area a = new Area(o.x1(), o.y1(), o.x1()+o.h(), o.y2());
		drawCheckBox(a);
		final Area b = o.child(o.x1()+o.h(), 0, o.x1()+o.h(), 0);
		drawText(b);
	}

	protected void drawCheckBox(final Area out) {
		final Area in = out.child(1, 1, -1, -1);
		RenderHelper.startShape();
		glColor4f(0.627451f, 0.627451f, 0.627451f, 1f);
		drawRect(out);
		glColor4f(0f, 0f, 0f, 1f);
		drawRect(in);
		RenderHelper.startTexture();
		if (this.checked) {
			final String strcheck = "\u2713";
			glPushMatrix();
			glTranslatef(in.x1()+(in.w()-font().getStringWidth(strcheck))/2, in.y1()+(in.h()-font().FONT_HEIGHT)/2, 0f);
			//glScalef(2f, 2f, 1f);
			font().drawStringWithShadow(strcheck, 0, 0, 0xffffff);
			glPopMatrix();
		}
	}
}
