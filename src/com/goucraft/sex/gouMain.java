package com.goucraft.sex;

import com.goucraft.listener.gouListeners;
import com.goucraft.sql.gouSql;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Administrator on 2017/2/6 0006.
 */
public class gouMain extends JavaPlugin {
    public static final ItemStack gold = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
    public static String prefix = "[苟性别]";
    private Vault vault;
    private Economy economy = null;
    private static gouMain Instance;

    public static gouMain getInstance() {
        return Instance;
    }

    public Economy getEconomy() {
        return this.economy;
    }

    public boolean hookVault() {
        this.vault = (Vault) this.getServer().getPluginManager().getPlugin("Vault");
        if (this.vault != null) {
            RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp != null) {
                this.economy = rsp.getProvider();
                this.getLogger().info("succeed");
            } else if (rsp == null) {
                this.getLogger().info("Failed to get economy");
            }
            this.getLogger().info("found vault");
        }
        return true;
    }

    @Override
    public void onEnable() {
        Instance = this;
        System.out.println(prefix + "启动中。。。");
        getServer().getPluginManager().registerEvents(new gouListeners(), this);
        this.getConfig().options().copyDefaults(true);
        saveConfig();
        hookVault();
        gouSql.startDatabaseConnection();
    }

    @Override
    public void onDisable() {
        System.out.println(prefix + "卸载中。。。");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("error");
            return true;
        }
        Player player = (Player) sender;
        if ((args.length == 1) && (args[0].equalsIgnoreCase("a"))) {
            Inventory inventory = Bukkit.createInventory(player, InventoryType.DISPENSER, "§6§l请选择性别");
            ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
            ItemMeta blackmeta = black.getItemMeta();
            blackmeta.setDisplayName("§5§l性别选择器");
            black.setItemMeta(blackmeta);

            ItemStack gold = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
            ItemMeta goldmeta = gold.getItemMeta();
            goldmeta.setDisplayName("§5§l男");
            gold.setItemMeta(goldmeta);

            ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
            ItemMeta redmeta = red.getItemMeta();
            redmeta.setDisplayName("§5§l女");
            red.setItemMeta(redmeta);

            int[] blacks = {0, 1, 2, 4, 6, 7, 8};
            for (int i = 0; i < blacks.length; i++) {
                inventory.setItem(blacks[i], black);
            }
            inventory.setItem(3, gold);
            inventory.setItem(5, red);
            player.openInventory(inventory);
        }
        return false;
    }

    //确认选择
    public static void mangui(CommandSender sender) {
        Player player;
        if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            return;
        }
        Inventory inventory = Bukkit.createInventory(player, InventoryType.DISPENSER, "§6§l确认选择男");
        ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta blackmeta = black.getItemMeta();
        blackmeta.setDisplayName("§5§l确认中");
        black.setItemMeta(blackmeta);

        ItemStack gold = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
        ItemMeta goldmeta = gold.getItemMeta();
        goldmeta.setDisplayName("§5§l确认");
        gold.setItemMeta(goldmeta);

        ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta redmeta = red.getItemMeta();
        redmeta.setDisplayName("§5§l取消");
        red.setItemMeta(redmeta);

        int[] blacks = {0, 1, 2, 3, 5, 6, 8};
        for (int i = 0; i < blacks.length; i++) {
            inventory.setItem(blacks[i], black);
        }
        inventory.setItem(4, gold);
        inventory.setItem(7, red);
        player.openInventory(inventory);
    }

    public static void womangui(CommandSender sender) {
        Player player;
        if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            return;
        }
        Inventory inventory = Bukkit.createInventory(player, InventoryType.DISPENSER, "§6§l确认选择男");
        ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta blackmeta = black.getItemMeta();
        blackmeta.setDisplayName("§5§l确认中");
        black.setItemMeta(blackmeta);

        ItemStack gold = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
        ItemMeta goldmeta = gold.getItemMeta();
        goldmeta.setDisplayName("§5§l确认");
        gold.setItemMeta(goldmeta);

        ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta redmeta = red.getItemMeta();
        redmeta.setDisplayName("§5§l取消");
        red.setItemMeta(redmeta);

        int[] blacks = {0, 1, 2, 3, 5, 6, 8};
        for (int i = 0; i < blacks.length; i++) {
            inventory.setItem(blacks[i], black);
        }
        inventory.setItem(4, gold);
        inventory.setItem(7, red);
        player.openInventory(inventory);
    }
}
