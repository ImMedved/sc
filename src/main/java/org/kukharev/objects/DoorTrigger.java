package org.kukharev.objects;

import org.kukharev.core.game.GameWorld;
import org.kukharev.core.game.LevelManager;

public class DoorTrigger extends Trigger {
    private String targetLevel;

    public DoorTrigger(float x, float y, float w, float h, String targetLevel) {
        super(x,y,w,h,"door");
        this.targetLevel = targetLevel;
    }

    @Override
    public void activate(LevelManager levelManager, GameWorld world) {
        levelManager.loadLevel(targetLevel);
        // Перезапустить мир или пересоздать GameWorld?
        // В данном случае GameApplication -> startGameAtLevel(targetLevel)
        // Но чтобы проще: при активации двери:
        world.dispose();
        world.loadCurrentLevel();
        // Либо вызвать game.startGameAtLevel(targetLevel) из мира, нужно добавить callback.
        // Это зависит от архитектуры. Можно сделать callback на game:
        // GameApplication game = ...; game.startGameAtLevel(targetLevel);
        // Но сейчас для простоты предположим, что world обновится, или переделать логику в GameScreen.
    }
}

