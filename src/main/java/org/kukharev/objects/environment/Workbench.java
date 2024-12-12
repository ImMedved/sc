package org.kukharev.objects.environment;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.objects.GameObject;
import org.kukharev.objects.items.EquipmentItem;
import org.kukharev.objects.items.Item;

import java.util.List;

public class Workbench extends GameObject {
    public Workbench(float x, float y){
        this.x=x;this.y=y;this.width=64;this.height=64;
    }

    // Например, крафт:
    public EquipmentItem craftEquipment(List<Item> materials) {
        // Проверить есть ли нужные ресурсы
        // Если есть - вернуть новый EquipmentItem
        return new EquipmentItem("New Armor");
    }

    @Override
    public void update(float delta){}
    @Override
    public void render(SpriteBatch batch){}
    @Override
    public void dispose(){}
}

