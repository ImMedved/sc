package org.kukharev.core.game;

public class TimeSystem {
    private float realSecondsPassed;
    // 1 час в игре = 6 мин реального времени
    // 1 игровой час = 60 мин
    // 6 мин реального = 360 сек реального = 1 ч игрового
    // Значит 1 реальная секунда = 1/360 игрового часа = 10 игров.секунд (проверить математику)
    // Проще: за 360 реальных секунд проходит 1 игровой час.
    // То есть 1 реальная минута (60 сек) = 10 игровых минут.

    // Хранить время в игровых минутах суток:
    private float gameMinutes; // 0 - 1440

    public void update(float delta) {
        realSecondsPassed += delta;
        // Каждые 6 реальных минут (360 секунд) проходит 1 игровой час (60 минут)
        // 1 реальная секунда = 60/360 = 0.166... игров. минут
        float gameMinutesPerRealSecond = 60f / 360f;
        gameMinutes += delta * 10; // приблизим: 1 сек = 10 игровых мин
        if (gameMinutes >= 1440) gameMinutes -= 1440; // новый день
    }

    public int getHour() {
        return (int)(gameMinutes / 60);
    }

    public int getMinute() {
        return (int)(gameMinutes % 60);
    }
}

