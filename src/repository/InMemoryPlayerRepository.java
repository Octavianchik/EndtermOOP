package repository;

import domain.Player;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPlayerRepository implements PlayerRepository {
    private final Map<String, Player> storage = new HashMap<>();

    @Override
    public void save(Player player) {
        storage.put(player.getName(), player);
    }

    @Override
    public Player findByName(String name) {
        return storage.get(name);
    }

    @Override
    public void deleteByName(String name) {
        storage.remove(name);
    }
}
