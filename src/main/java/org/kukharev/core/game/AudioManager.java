package org.kukharev.core.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    private static Music menuMusic;
    private static Music gameMusic;
    private static Sound footstepSound;
    private static Sound attackSound;
    private static boolean inGame = false;

    public static void load() {
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/music/menu_theme.ogg"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/music/game_theme.ogg"));
        footstepSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/footstep.wav"));
        attackSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/attack.wav"));
    }

    public static void playMenuMusic() {
        stopAll();
        menuMusic.setLooping(true);
        menuMusic.play();
        inGame=false;
    }

    public static void playGameMusic() {
        stopAll();
        gameMusic.setLooping(true);
        gameMusic.play();
        inGame=true;
    }

    public static void footstep() {
        if (inGame) footstepSound.play();
    }

    public static void attack() {
        if (inGame) attackSound.play();
    }

    public static void stopAll() {
        menuMusic.stop();
        gameMusic.stop();
    }
}
