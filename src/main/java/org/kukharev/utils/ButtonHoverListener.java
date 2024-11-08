package org.kukharev.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ButtonHoverListener extends InputListener {
    private final ImageButton button;
    private final TextureRegion normalRegion;
    private final TextureRegion hoverRegion;
    private final String action;
    private final java.util.function.Consumer<String> actionHandler;

    public ButtonHoverListener(ImageButton button, TextureRegion normalRegion, TextureRegion hoverRegion, String action, java.util.function.Consumer<String> actionHandler) {
        this.button = button;
        this.normalRegion = normalRegion;
        this.hoverRegion = hoverRegion;
        this.action = action;
        this.actionHandler = actionHandler;
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        button.getStyle().imageUp = new TextureRegionDrawable(hoverRegion);
        return true;
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
        button.getStyle().imageUp = new TextureRegionDrawable(normalRegion);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttonCode) {
        button.addAction(Actions.sequence(
                Actions.moveBy(0, -5, 0.1f), // Press down
                Actions.moveBy(0, 5, 0.2f),  // Return to original position
                Actions.run(() -> actionHandler.accept(action)) // Trigger action
        ));
        return true;
    }
}
