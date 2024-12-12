package org.kukharev.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mob extends GameObject {
    private float health = 100;
    private float speed = 50;
    private float attackRange = 30;
    private float attackDamage = 10;
    public Mob(float x, float y) {
        this.x=x;this.y=y;this.width=32;this.height=32;
    }

    public void damage(float amount) {
        health-=amount;
    }

    public boolean isDead() {return health<=0;}

    @Override
    public void update(float delta){}
    @Override
    public void render(SpriteBatch batch){}
    @Override
    public void dispose(){}
}

