package org.daniel.spiral.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SPanel extends JPanel {
	
	private Point[] points;
	private Point center;
	
	private int radius = 500;

	public static void main(String[] args) {
		new SPanel();

	}
	
	private SPanel() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setContentPane(this);
		frame.setVisible(true);
		
		this.initPoints(5);
		this.center = new Point(frame.getWidth() / 2, frame.getHeight() / 2);
		radius = center.y - 25;
		this.setBackground(new Color(0.2f, 0.2f, 0.2f));
		
		long now = System.nanoTime(), lastLoop = now, loopNanos = 1000000000 / 100;
		
		while(true)
			if((now = System.nanoTime()) - lastLoop >= loopNanos) {
				lastLoop = now;
				this.repaint();
			}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.initPoints(this.points.length + 1);
		for(int i = 0; i < this.points.length - 1; i++) {
			g.setColor(Color.getHSBColor((float) i / this.points.length, 1, 1));
			g.drawLine(this.points[i].x + this.center.x, this.points[i].y + this.center.y, this.points[i + 1].x + this.center.x, this.points[i + 1].y + this.center.y);
		}
	}
	
	private void initPoints(int index) {
		this.points = new Point[index];
		for(int i = 0; i < index; i++) this.points[i] = new Point((int) (((float) radius / index) * (index - i) * Math.cos(Math.PI / 2 * i + Math.PI / 100 * i)), (int) (((float) radius / index) * (index - i) * Math.sin(Math.PI / 2 * i + Math.PI / 100 * i)));
	}

}
