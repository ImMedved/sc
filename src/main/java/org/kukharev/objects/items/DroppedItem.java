package org.kukharev.objects.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.objects.GameObject;

public class DroppedItem extends GameObject {
    private Item item;

    public DroppedItem(Item item, float x, float y) {
        this.item = item;
        this.x = x;
        this.y = y;
        // width,height задать
    }

    @Override
    public void update(float delta){}
    @Override
    public void render(SpriteBatch batch){
        // нарисовать иконку предмета
    }
    @Override
    public void dispose(){}
    public Item getItem(){return item;}
}

