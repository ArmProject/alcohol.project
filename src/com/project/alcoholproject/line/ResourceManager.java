package com.project.alcoholproject.line;

import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ResourceManager {
	private static ResourceManager instance;

	VertexBufferObjectManager vbo;
	public IFont font;

	public static ResourceManager getInstance() {
		return instance;
	}

	public ResourceManager(VertexBufferObjectManager vbo) {
		this.vbo = vbo;
		instance = this;
	}

	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return vbo;
	}

	public void setFont(IFont font) {
		this.font = font;
	}

	public IFont getFont() {
		font.load();
		return font;
	}
}
