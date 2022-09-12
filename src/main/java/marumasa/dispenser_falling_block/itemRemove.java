package marumasa.dispenser_falling_block;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class itemRemove extends BukkitRunnable {

    private final Inventory inv;
    private final ItemStack[] itemStacks;
    private final Material mat;

    public itemRemove(Inventory inventory, Material material) {
        inv = inventory;
        itemStacks = inventory.getContents();
        mat = material;
    }

    public void run() {
        this.cancel();
        final List<ItemStack> removeItems = new ArrayList<>();
        for (ItemStack itemStack : itemStacks) {
            if (itemStack == null) continue;
            if (itemStack.getType() != mat) continue;
            removeItems.add(itemStack);
        }
        if (removeItems.size() == 0) {
            inv.setContents(itemStacks);
        } else {
            Random rand = new Random();
            ItemStack item = removeItems.get(rand.nextInt(removeItems.size()));
            item.setAmount(item.getAmount() - 1);
        }
    }
}
