package com.suppergerrie2.voicewarning.voicewarning;

import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoiceWarning extends JavaPlugin {

    @Override
    public void onEnable() {
        BukkitVoicechatService service = getServer().getServicesManager()
                                                    .load(BukkitVoicechatService.class);
        if (service != null) {
            service.registerPlugin(new WarnPlugin(this));
        }

        this.getConfig()
            .options()
            .copyDefaults(true);
        this.saveConfig();
    }

}
