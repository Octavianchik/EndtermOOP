package repository;

import domain.Player;

public interface PlayerRepository {
    void save(Player player);

    Player findByName(String name);

    void deleteByName(String name);
}
