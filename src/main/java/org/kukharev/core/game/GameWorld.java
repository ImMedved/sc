package org.kukharev.core.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import org.kukharev.objects.GameObject;
import org.kukharev.objects.Player;
import org.kukharev.objects.Trigger;
import org.kukharev.systems.*;

import java.util.List;

public class GameWorld {
    private final OrthographicCamera camera;
    private final Player player;
    private TiledMap map;
    private final SystemManager systemManager;
    private final TimeSystem timeSystem;
    private final InventorySystem inventorySystem;
    private final LevelManager levelManager;
    private List<GameObject> worldObjects; // включает двери, печь, верстак, ресурсы

    public GameWorld(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Загрузка первого уровня через levelManager
        loadCurrentLevel();

        //Временное исправление для отладки
        //TODO: предоставить текстуры
        this.player = new Player(null,null,null,null,null,null);
        this.timeSystem = new TimeSystem();
        this.inventorySystem = new InventorySystem(player);

        systemManager = new SystemManager();
        systemManager.addSystem(new InputSystem());
        systemManager.addSystem(new MovementSystem(player));
        systemManager.addSystem(new CombatSystem(player, player.getCombatTextures()));
        systemManager.addSystem(new ActionSystem(player, player.getActionTextures()));
        systemManager.addSystem(new MobSystem(this));
        systemManager.addSystem(new FurnaceSystem(this));
        systemManager.addSystem(new WorkbenchSystem(this));
        systemManager.addSystem(inventorySystem);
        // при необходимости CollisionSystem, MobSpawnSystem

        // Добавляем триггеры дверей
        worldObjects = levelManager.getWorldObjectsForCurrentLevel();
    }

    public void loadCurrentLevel() {
        this.map = levelManager.getCurrentMap();
    }

    public void update(float delta) {
        timeSystem.update(delta);
        systemManager.updateSystems(delta);
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        // Проверка триггеров (включая DoorTrigger)
        for (GameObject obj : worldObjects) {
            if (obj instanceof Trigger) {
                Trigger trig = (Trigger)obj;
                if (trig.getArea().overlaps(player.getBounds())) {
                    trig.activate(levelManager, this);
                }
            }
        }
    }

    public TimeSystem getTimeSystem() {
        return timeSystem;
    }

    public Player getPlayer() {
        return player;
    }

    public TiledMap getMap() {
        return map;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public List<GameObject> getWorldObjects() {
        return worldObjects;
    }

    public void dispose() {
        map.dispose();
        player.dispose();
    }
}
