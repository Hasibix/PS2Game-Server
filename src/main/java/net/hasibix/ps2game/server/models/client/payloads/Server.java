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
    
    public Server SetName(String name) {
        this.name = !ObjUtils.String.IsEmpty(name) ? name : "Server";
        return this;
    }
    
    public Server SetIcon(String iconUrl) {
        this.iconUrl = !ObjUtils.String.IsEmpty(iconUrl) ? iconUrl : "";
        return this;
    }

    public Server SetMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers < 20 && maxPlayers > 0 ? maxPlayers : 20;
        return this;
    }

    public Server JoinAs(User player) {
        players.putIfAbsent(player.GetId(), player);
        return this;
    }

    public String GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public String GetIconUrl() {
        return iconUrl;
    }

    public int GetMaxPlayers() {
        return maxPlayers;
    }

    public LinkedHashMap<String, User> GetCurrentPlayers() {
        return this.players;
    }

    public void PushToDB() {

    }

    public static Server PullFromDB() {

        return null;
    }
}
