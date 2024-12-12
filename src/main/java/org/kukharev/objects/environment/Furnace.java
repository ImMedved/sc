package org.kukharev.objects.environment;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.objects.GameObject;
import org.kukharev.objects.items.ProcessedResourceItem;
import org.kukharev.objects.items.RawResourceItem;

public class Furnace extends GameObject {
    private float timer;
    private RawResourceItem processingItem;
    public Furnace(float x, float y) {
        this.x=x;this.y=y;this.width=64;this.height=64;
    }

    public void insertRawItem(RawResourceItem item) {
        this.processingItem = item;
        timer=10f; // 10 сек обработка
    }

    public ProcessedResourceItem retrieveProcessedItem() {
        if (processingItem==null || timer>0) return null;
        // вернуть обработанный предмет
        ProcessedResourceItem p = new ProcessedResourceItem("Processed "+processingItem.getName());
        processingItem=null;
        return p;
    }

    @Override
    public void update(float delta) {
        if (processingItem!=null && timer>0) {
            timer-=delta;
            if (timer<0) timer=0;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        // нарисовать печь
    }
    @Override
    public void dispose(){}
}

