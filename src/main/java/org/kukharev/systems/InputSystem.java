package org.kukharev.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputSystem implements GameSystem {
    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            // Обработка движения вверх
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            // Обработка движения влево
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            // Обработка движения вниз
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            // Обработка движения вправо
        }
    }

    @Override
    public void render() {

    }
}
