package marumasa.dispenser_falling_block;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public final String[] materials;
    public final boolean Log;

    public Config(minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        materials = config.getStringList("BlockList").toArray(new String[0]);
        Log = config.getBoolean("Log");
    }
}