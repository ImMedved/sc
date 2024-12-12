package org.kukharev.core.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.kukharev.core.desktop.GameApplication;
import org.kukharev.objects.*;
import org.kukharev.objects.environment.Furnace;
import org.kukharev.objects.environment.Workbench;
import org.kukharev.utils.managers.AssetLoader;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    private final GameApplication game;
    private final AssetLoader assetLoader;
    private String currentLevelName;
    private TiledMap currentMap;
    private List<GameObject> worldObjects;

    public LevelManager(GameApplication game, AssetLoader assetLoader) {
        this.game = game;
        this.assetLoader = assetLoader;
    }

    public void loadLevel(String levelName) {
        this.currentLevelName = levelName;
        currentMap = new TmxMapLoader().load(levelName);
        // Загрузить объекты уровня: двери, печи, верстак, мобов, предметы...
        worldObjects = generateObjectsForLevel(levelName);
    }

    private List<GameObject> generateObjectsForLevel(String levelName) {
        List<GameObject> objects = new ArrayList<>();
        if (levelName.equals("level1.tmx")) {
            // добавить DoorTrigger к "level2.tmx"
            objects.add(new DoorTrigger(400,300,50,50,"level2.tmx"));
            objects.add(new Furnace(600,500));
            objects.add(new Workbench(650,500));
        } else if (levelName.equals("level2.tmx")) {
            // Враждебный моб
            objects.add(new Mob(700,700));
            // Дверь назад
            objects.add(new DoorTrigger(100,100,50,50,"level1.tmx"));
        }
        return objects;
    }

    public TiledMap getCurrentMap() {
        return currentMap;
    }

    public List<GameObject> getWorldObjectsForCurrentLevel() {
        return worldObjects;
    }
}

