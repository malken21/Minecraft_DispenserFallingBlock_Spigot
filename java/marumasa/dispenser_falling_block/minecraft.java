package marumasa.dispenser_falling_block;

import org.bukkit.plugin.java.JavaPlugin;

public final class minecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        Config config = new Config(this);
        getServer().getPluginManager().registerEvents(new eventListener(config, this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
