package org.kukharev.systems;

import org.kukharev.objects.Player;

public class InventorySystem implements GameSystem {
    private Player player;

    public InventorySystem(Player player) {
        this.player = player;
    }

    @Override
    public void update(float delta) {
        // Проверяем если игрок стоит на DroppedItem
        // Если да и нажата клавиша взаимодействия - подбираем предмет
    }

    @Override
    public void render() {}
}
