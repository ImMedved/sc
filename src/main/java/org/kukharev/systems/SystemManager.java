package org.kukharev.systems;

import java.util.ArrayList;
import java.util.List;

public class SystemManager {
    private final List<GameSystem> systems = new ArrayList<>();

    public void addSystem(GameSystem system) {
        systems.add(system);
    }

    public void updateSystems(float delta) {
        for (GameSystem system : systems) {
            system.update(delta);
        }
    }

    public void renderSystems() {
        for (GameSystem system : systems) {
            system.render();
        }
    }
}
