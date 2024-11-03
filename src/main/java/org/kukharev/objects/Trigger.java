package org.kukharev.objects;

import com.badlogic.gdx.math.Rectangle;

public class Trigger {
    private final Rectangle area;
    private final String targetLevel;

    public Trigger(float x, float y, float width, float height, String targetLevel) {
        area = new Rectangle(x, y, width, height);
        this.targetLevel = targetLevel;
    }

    public Rectangle getArea() {
        return area;
    }

    public String getTargetLevel() {
        return targetLevel;
    }
}
