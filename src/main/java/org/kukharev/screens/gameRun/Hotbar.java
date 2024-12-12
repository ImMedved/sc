package org.kukharev.screens.gameRun;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.objects.Player;

public class Hotbar {
    private Player player;
    // Допустим 10 слотов
    public Hotbar(Player player) {
        this.player = player;
    }

    public void render(SpriteBatch batch) {
        // Нарисовать прямоугольники слотов и иконки предметов из инвентаря, помеченных как хотбарные
    }
}

