package marumasa.dispenser_falling_block;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;

public class eventListener implements Listener {

    private final Config con;
    private final minecraft mc;

    public eventListener(Config config, minecraft minecraft) {
        con = config;
        mc = minecraft;
    }

    @EventHandler
    public void onEntity(BlockDispenseEvent event) {
        final ItemStack item = event.getItem();
        final Material itemM = item.getType();
        final String itemMS = itemM.toString();

        if (con.Log) Bukkit.getLogger().info(itemMS);

        for (String ConfigM : con.materials) {
            if (!ConfigM.equalsIgnoreCase(itemMS)) continue;
            //-----start!!-----//
            final Block block = event.getBlock();

            final Location loc = block.getLocation().add(0.5, 0, 0.5);
            final String facing = block.getBlockData().getAsString().split("[=,]")[1];

            switch (facing) {
                case "east" -> loc.add(1, 0, 0);
                case "west" -> loc.add(-1, 0, 0);
                case "up" -> loc.add(0, 1, 0);
                case "down" -> loc.add(0, -1, 0);
                case "south" -> loc.add(0, 0, 1);
                case "north" -> loc.add(0, 0, -1);
            }
            final World world = block.getWorld();

            final Dispenser dispenser = (Dispenser) event.getBlock().getState();
            new itemRemove(dispenser.getInventory(), itemM).runTaskTimer(mc, 0L, 0L);
            world.spawnFallingBlock(loc, itemM.createBlockData());
            event.setCancelled(true);
            break;
        }
    }
}
