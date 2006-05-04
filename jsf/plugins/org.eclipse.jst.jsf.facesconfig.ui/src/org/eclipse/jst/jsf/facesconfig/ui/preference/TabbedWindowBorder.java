/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.preference;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SchemeBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class TabbedWindowBorder extends FrameBorder {
	private boolean isVisible = true;

	// CR392586: resource leaks
	private static MySchemeBorder normalBorder = new MySchemeBorder(
			new MySchemeBorder.MyScheme(
					new Color[] { ColorConstants.button,
							ColorConstants.buttonLightest,
							ColorConstants.button },
					new Color[] { ColorConstants.buttonDarkest,
							ColorConstants.buttonDarker, ColorConstants.button }));

	// CR392586: resource leaks
	private static MySchemeBorder highlightBorder = new MySchemeBorder(
			new MySchemeBorder.MyScheme(new Color[] {
					FigureUtilities.lighter(ColorConstants.titleBackground),
					ColorConstants.titleBackground,
					FigureUtilities.darker(ColorConstants.titleBackground) },
					new Color[] {
							FigureUtilities
									.darker(ColorConstants.titleGradient),
							ColorConstants.titleGradient,
							FigureUtilities
									.lighter(ColorConstants.titleGradient) }));

	public static class MySchemeBorder extends SchemeBorder {
		public static class MyScheme extends SchemeBorder.Scheme {
			public MyScheme(Color[] highlight, Color[] shadow) {
				super(highlight, shadow);
			}

			public Color[] getHighlight() {
				return super.getHighlight();
			}

			public Color[] getShadow() {
				return super.getHighlight();
			}
		};

		MySchemeBorder(MyScheme scheme) {
			super(scheme);
		}

		public MyScheme getMyScheme() {
			return (MyScheme) super.getScheme();
		}

		/**
		 * @see Border#paint(IFigure, Graphics, Insets)
		 */
		public void paint(Composite comp, GC gc, Insets insets) {
			Color[] tl = getMyScheme().getHighlight();
			Color[] br = getMyScheme().getShadow();
			paint(comp, gc, insets, tl, br);
		}

		/**
		 * Paints the border using the information in the set Scheme and the
		 * inputs given. Side widths are determined by the number of colors in
		 * the Scheme for each side.
		 * 
		 * @param graphics
		 *            the graphics object
		 * @param fig
		 *            the figure this border belongs to
		 * @param insets
		 *            the insets
		 * @param tl
		 *            the highlight (top/left) colors
		 * @param br
		 *            the shadow (bottom/right) colors
		 */
		protected void paint(Composite comp, GC gc, Insets insets, Color[] tl,
				Color[] br) {
			org.eclipse.swt.graphics.Rectangle rect = comp.getBounds();

			gc.setLineWidth(1);
			gc.setLineStyle(SWT.LINE_SOLID);
			gc.setXORMode(false);

			int top = rect.y - insets.top;
			int left = rect.x - insets.left;
			int bottom = rect.y + rect.height + insets.bottom;
			int right = rect.x + rect.width + insets.right;
			gc.setClipping(new org.eclipse.swt.graphics.Rectangle(left, top,
					right - left, bottom - top));

			Color color = new Color(Display.getDefault(), new RGB(255, 0, 0));
			gc.setForeground(color);
			gc.drawLine(left, top, right, bottom);

			for (int i = 0; i < br.length; i++) {
				// color = br[i];
				gc.setForeground(color);
				gc.drawLine(right - i, bottom - i, right - i, top + i);
				gc.drawLine(right - i, bottom - i, left + i, bottom - i);
			}

			right--;
			bottom--;

			for (int i = 0; i < tl.length; i++) {
				// color = tl[i];
				gc.setForeground(color);
				gc.drawLine(left + i, top + i, right - i, top + i);
				gc.drawLine(left + i, top + i, left + i, bottom - i);
			}
			color.dispose();
		}
	}

	public TabbedWindowBorder(IFigure parent) {
		// apparently paint() gets called before createBorders() has had
		// a chance to create the borders, so we just create them here
		inner = new TabbedTitleBarBorder(parent);
		outer = normalBorder;
	}

	public void setVisible(boolean flag) {
		if (isVisible != flag) {
			isVisible = flag;
			((TabbedTitleBarBorder) inner).setVisible(flag);
			if (flag)
				((TabbedTitleBarBorder) inner).getParent().repaint();
		}
	}

	public void setHighlight(boolean flag) {
		if (flag)
			outer = highlightBorder;
		else
			outer = normalBorder;
		((TabbedTitleBarBorder) inner).getParent().repaint();
	}

	protected void createBorders() {
	}

	public void paint(IFigure figure, Graphics g, Insets insets) {
		if (isVisible) {
			if (comp != null) {
				GC gc = new GC(comp);
				paint(comp, gc, insets);
				gc.dispose();
			} else
				super.paint(figure, g, insets);
		}
	}

	private Composite comp;

	public void paint(Composite comp, GC gc, Insets insets) {
		this.comp = comp;
		if (isVisible)
			((MySchemeBorder) outer).paint(comp, gc, insets);
	}
}