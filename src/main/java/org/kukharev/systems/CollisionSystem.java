package org.kukharev.systems;

import org.kukharev.objects.GameObject;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

public class CollisionSystem implements GameSystem {
    private final List<GameObject> gameObjects;

    public CollisionSystem(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    @Override
    public void update(float delta) {
        for (int i=0; i<gameObjects.size(); i++) {
            GameObject objA = gameObjects.get(i);
            for (int j = i+1; j<gameObjects.size(); j++) {
                GameObject objB = gameObjects.get(j);
                if (checkCollision(objA,objB)) {
                    handleCollision(objA,objB);
                }
            }
        }
    }

    private boolean checkCollision(GameObject objA, GameObject objB) {
        Rectangle a = objA.getBounds();
        Rectangle b = objB.getBounds();
        return a.overlaps(b);
    }

    private void handleCollision(GameObject objA, GameObject objB) {
        // TODO: Реализовать логику столкновений
    }

    @Override
    public void render() {}
}
