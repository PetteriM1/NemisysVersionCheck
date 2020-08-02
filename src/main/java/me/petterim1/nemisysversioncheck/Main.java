package me.petterim1.nemisysversioncheck;

import org.itxtech.nemisys.Player;
import org.itxtech.nemisys.event.EventHandler;
import org.itxtech.nemisys.event.Listener;
import org.itxtech.nemisys.event.player.PlayerLoginEvent;
import org.itxtech.nemisys.plugin.PluginBase;

public class Main extends PluginBase implements Listener {

    private int protocol;
    private String message;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        protocol = getConfig().getInt("min-raknet-protocol");
        message = getConfig().getString("message");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if (p.raknetProtocol < protocol) {
            p.close(message, true);
        }
    }
}
