package com.project.alcoholproject.game;

import java.util.Random;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import android.util.Log;

public class PointFactory {
	Scene scene;
	VertexBufferObjectManager vbo;
	Random rnd = new Random();

	float x1 = 0, y1 = 0, x2 = 0, y2 = 0;

	public PointFactory(Scene scene, VertexBufferObjectManager vbo) {
		this.scene = scene;
		this.vbo = vbo;

		scene.setTouchAreaBindingOnActionDownEnabled(true);

		scene.setOnSceneTouchListener(new IOnSceneTouchListener() {

			@Override
			public boolean onSceneTouchEvent(Scene pScene,
					TouchEvent pSceneTouchEvent) {
				if (!(x1 == 0) && !(y1 == 0)) {
					x2 = pSceneTouchEvent.getX();
					y2 = pSceneTouchEvent.getY();
					drawLine();
					return true;
				}
				return false;
			}
		});
	}

	public void random(int n, int limx, int limy) {

		for (int i = 0; i < n; i++) {
			int x = rnd.nextInt(limx);
			int y = rnd.nextInt(limy);
			int r = rnd.nextInt(100) + 10;
			createPoint(x, y, r);
		}
	}

	private void createPoint(int x, int y, int r) {
		Rectangle rec = new Rectangle(x, y, r, r, vbo) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				float xPos = this.getX() + this.getWidth() / 2;
				float yPos = this.getY() + this.getHeight() / 2;
				if (x1 == 0 && y1 == 0) {
					x1 = xPos;
					y1 = yPos;
				}
				x2 = xPos;
				y2 = yPos;

				drawLine();

				x1 = x2;
				y1 = y2;

				return true;
			}

		};

		float red = rnd.nextFloat();
		float green = rnd.nextFloat();
		float blue = rnd.nextFloat();
		rec.setColor(new Color(red, green, blue));

		scene.registerTouchArea(rec);
		scene.attachChild(rec);
	}

	private void drawLine() {
		Line line = new Line(x1, y1, x2, y2, vbo);
		line.setColor(Color.BLACK);
		line.setLineWidth(5);

		scene.attachChild(line);
	}
}
