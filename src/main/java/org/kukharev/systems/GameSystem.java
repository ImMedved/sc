package org.kukharev.systems;

public interface GameSystem {
    void update(float delta);
    void render(); // Можно не использовать, если не нужен
}
