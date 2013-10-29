package com.project.alcoholproject.game;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.font.Letter;
import org.andengine.opengl.font.exception.LetterNotFoundException;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.call.Callback;
import org.andengine.util.color.Color;

import android.util.Log;

public class PointFactory {
	int gap = 20;
	int size = 80;

	Scene scene;
	VertexBufferObjectManager vbo;
	Callback<Object> onTop, onFinish;

	Random rnd = new Random();
	ArrayList<Rectangle> list = new ArrayList<Rectangle>();
	float x1 = 0, y1 = 0, x2 = 0, y2 = 0;
	Line line;
	Rectangle start;

	public PointFactory(Scene sc) {
		this.scene = sc;
		this.vbo = ResourceManager.getInstance().getVertexBufferObjectManager();
		scene.setTouchAreaBindingOnActionDownEnabled(true);

		scene.setOnSceneTouchListener(new IOnSceneTouchListener() {

			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent e) {

				if (start.contains(e.getX(), e.getY())) {
					x1 = start.getX() + start.getWidth() / 2;
					y1 = start.getY() + start.getHeight() / 2;
					onTop.onCallback(0);
				}

				if (x1 != 0 && y1 != 0) {
					x2 = e.getX();
					y2 = e.getY();
					drawLine();
				}
				return true;
			}
		});
	}

	public void random(int[] data, int d, int n, int limx, int limy) {
		float x = 0, y = 0;
		float r = size + gap;
		for (int i = 0; i < n; i++) {
			if (i / d == 0) {
				x = (limx - d * r) / 2 + (i % d) * r;
				y = limy - r;
			} else if (i / d == 1) {
				x = (limx - d * r) / 2 + (i % d) * r;
				y = 0;
			} else if (i / d == 2) {
				y = (limy - d * r) / 2 + (i % d) * r;
				// x = (limx - d * r) / 2 - r;
				x = 0;
			} else if (i / d == 3) {
				y = (limy - d * r) / 2 + (i % d) * r;
				// x = (limx - d * r) / 2 + d * r;
				x = limx - r;
			}
			int index = data[i];
			createPoint(index, x, y, size, index == 0);
		}

	}

	public void setOnTop(Callback<Object> callback) {
		this.onTop = callback;
	}

	public void finish() {
		scene.setOnSceneTouchListener(null);
		for (Object obj : list) {
			scene.unregisterTouchArea((ITouchArea) obj);
		}
	}

	private void createPoint(final int i, float x, float y, float r,
			boolean isSeed) {
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
				line = null;

				x1 = x2;
				y1 = y2;

				onTop.onCallback(i);
				return true;
			}

		};

		float red = rnd.nextFloat();
		float green = rnd.nextFloat();
		float blue = rnd.nextFloat();
		rec.setColor(new Color(red, green, blue));

		String ch = (i + 1) + "";
		TextOptions option = new TextOptions(HorizontalAlign.CENTER);
		Text text = new Text(0, 0, ResourceManager.getInstance().getFont(), ch,
				option, vbo);
		text.setPosition((rec.getWidth() - text.getWidth()) / 2,
				(rec.getHeight() - text.getHeight()) / 2);

		rec.attachChild(text);
		if (isSeed) {
			start = rec;
		} else {
			scene.registerTouchArea(rec);
		}
		list.add(rec);
		scene.attachChild(rec);
	}

	private void drawLine() {
		if (line != null) {
			scene.detachChild(line);
		}

		line = new Line(x1, y1, x2, y2, vbo);
		line.setColor(Color.BLACK);
		line.setLineWidth(8);
		scene.attachChild(line);

		line.setZIndex(-10);
		scene.sortChildren();
	}

}
