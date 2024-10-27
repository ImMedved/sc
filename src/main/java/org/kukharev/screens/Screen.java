package org.kukharev.screens;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class Screen extends Group {
    public abstract void render(float delta); // Добавлен метод render()
    public abstract void dispose();
}
