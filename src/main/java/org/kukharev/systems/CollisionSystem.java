package org.kukharev.systems;

import org.kukharev.objects.GameObject;

import java.util.List;

public class CollisionSystem implements GameSystem {
    private final List<GameObject> gameObjects;

    public CollisionSystem(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    @Override
    public void update(float delta) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject objA = gameObjects.get(i);
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject objB = gameObjects.get(j);
                if (checkCollision(objA, objB)) {
                    handleCollision(objA, objB);
                }
            }
        }
    }

    private boolean checkCollision(GameObject objA, GameObject objB) {
        // Логика проверки столкновения
        return objA.getBounds().overlaps(objB.getBounds());
    }

    private void handleCollision(GameObject objA, GameObject objB) {
        // Обработка столкновения, например, уменьшение здоровья
    }

    @Override
    public void render() {
        // Опциональный рендеринг для визуализации столкновений
    }
}
