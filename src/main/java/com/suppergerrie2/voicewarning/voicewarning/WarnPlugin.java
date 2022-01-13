package com.suppergerrie2.voicewarning.voicewarning;

import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.PlayerConnectedEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class WarnPlugin implements VoicechatPlugin {

    private final VoiceWarning plugin;

    public WarnPlugin(VoiceWarning plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getPluginId() {
        return "suppergerrie2:warning-plugin";
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(PlayerConnectedEvent.class, this::playerConnected);
    }

    /***
     * Handles the player connecting event and sends the message to the player that just connected.
     * Note that this is connecting to the voice server and not the minecraft server and will thus only be called for players with the mod.
     *
     * @param playerConnectedEvent The event with the player that just connected.
     */
    private void playerConnected(PlayerConnectedEvent playerConnectedEvent) {
        var playerObject = playerConnectedEvent.getConnection()
                                               .getPlayer()
                                               .getPlayer();

        if (playerObject instanceof Player player) {
            player.sendMessage(getMessage());
        } else {
            Bukkit.getLogger()
                  .info("Player is not a player! It's %s".formatted(playerObject.getClass()
                                                                                .getName()));
        }
    }

    private String getMessage() {
        var message = plugin.getConfig()
                            .getString("messages.voicechat_warning");

        message = Objects.requireNonNullElse(message, "Someone forgot to set a message, please report this!");
        message = ChatColor.translateAlternateColorCodes('&', message);

        return message;
    }
}
