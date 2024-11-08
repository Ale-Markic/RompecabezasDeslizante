package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class BotonesModificados extends JButton {

	// Radios para las esquinas (superior izquierda, superior derecha, inferior derecha, inferior izquierda)
	private int topLeftRadius;
	private int topRightRadius;
	private int bottomRightRadius;
	private int bottomLeftRadius;

	public BotonesModificados(int topLeftRadius, int topRightRadius, int bottomRightRadius, int bottomLeftRadius) {
		super();
		this.topLeftRadius = topLeftRadius;
		this.topRightRadius = topRightRadius;
		this.bottomRightRadius = bottomRightRadius;
		this.bottomLeftRadius = bottomLeftRadius;
		setOpaque(false);  // Hace que el panel sea transparente
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());

		Path2D.Double path = new Path2D.Double();

		path.moveTo(topLeftRadius, 0);
		path.lineTo(getWidth() - topRightRadius, 0);
		path.quadTo(getWidth(), 0, getWidth(), topRightRadius);
		path.lineTo(getWidth(), getHeight() - bottomRightRadius);
		path.quadTo(getWidth(), getHeight(), getWidth() - bottomRightRadius, getHeight());
		path.lineTo(bottomLeftRadius, getHeight());
		path.quadTo(0, getHeight(), 0, getHeight() - bottomLeftRadius);
		path.lineTo(0, topLeftRadius);
		path.quadTo(0, 0, topLeftRadius, 0);


		g2.fill(path);
	}

	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(getForeground());

		Path2D.Double path = new Path2D.Double();

		path.moveTo(topLeftRadius, 0);
		path.lineTo(getWidth() - topRightRadius, 0);
		path.quadTo(getWidth(), 0, getWidth(), topRightRadius);
		path.lineTo(getWidth(), getHeight() - bottomRightRadius);
		path.quadTo(getWidth(), getHeight(), getWidth() - bottomRightRadius, getHeight());
		path.lineTo(bottomLeftRadius, getHeight());
		path.quadTo(0, getHeight(), 0, getHeight() - bottomLeftRadius);
		path.lineTo(0, topLeftRadius);
		path.quadTo(0, 0, topLeftRadius, 0);

		g2.draw(path);
	}
}
