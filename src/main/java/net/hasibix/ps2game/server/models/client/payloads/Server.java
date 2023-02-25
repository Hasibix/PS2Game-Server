package net.hasibix.ps2game.server.models.client.payloads;

import java.security.SecureRandom;
import java.util.LinkedHashMap;
import javax.annotation.Nonnull;
import net.hasibix.ps2game.server.utils.ObjUtils;

public class Server {
    @Nonnull private String id;
    @Nonnull private String name = "Server";
    @Nonnull private String iconUrl;
    @Nonnull private int maxPlayers = 20;
    @Nonnull private LinkedHashMap<String, User> players;

    SecureRandom random = new SecureRandom();

    public Server() {
        this.id = Integer.toString(random.nextInt(10));
    }
    
    public Server setName(String name) {
        this.name = !ObjUtils.String.isEmpty(name) ? name : "Server";
        return this;
    }
    
    public Server setIcon(String iconUrl) {
        this.iconUrl = !ObjUtils.String.isEmpty(iconUrl) ? iconUrl : "";
        return this;
    }

    public Server setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers < 20 && maxPlayers > 0 ? maxPlayers : 20;
        return this;
    }

    public Server joinAs(User player) {
        players.putIfAbsent(player.getId(), player);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public LinkedHashMap<String, User> getCurrentPlayers() {
        return this.players;
    }

    public Server saveAndSync() {
        // Save to database....
        return this;
    }
}
