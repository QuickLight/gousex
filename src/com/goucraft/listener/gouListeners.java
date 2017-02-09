package com.goucraft.listener;

import code.nextgen.sqlibrary.model.utils.Condition;
import code.nextgen.sqlibrary.model.utils.ConditionBuilder;
import com.goucraft.sex.gouMain;
import com.goucraft.sql.gouPlayerFactory;
import com.goucraft.sql.gouPlayerModel;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * Created by Administrator on 2017/2/6 0006.
 */
public class gouListeners implements Listener {
    private final gouMain plugin = gouMain.getInstance();
    private Economy economy = plugin.getEconomy();
    String prefix;
    String marry;


    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = Bukkit.getPlayer(event.getPlayer().getName());
        ConditionBuilder conditionBuilder = new ConditionBuilder(Condition.fieldEquals("player_uuid", player.getUniqueId(),
                gouPlayerFactory.getInstance().getProfile())).and(Condition.fieldEquals("player_sex", 0, gouPlayerFactory.getInstance().getProfile()));
        List<gouPlayerModel> models = gouPlayerFactory.getInstance().select(conditionBuilder.build());
        if (models == null) {
            //报错了
            return;
        }
        if (models.size() < 1) {
            //已加入过服务器
            Inventory inventory = Bukkit.createInventory(player, InventoryType.DISPENSER, "§6§l请选择性别");
            event.getPlayer().openInventory(inventory);
            return;
        }
        if (models.size() == 1) {
            //未加入过服务器召唤GUI
            //如果选择了性别 修改数据库player_sex 并修改聊天前缀
            Inventory inventory = Bukkit.createInventory(player, InventoryType.DISPENSER, "§6§l请选择性别");
            event.getPlayer().openInventory(inventory);
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        if (event.getClickedInventory().getName().equals("§6§l请选择性别")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5§l男")) {
                        gouMain.mangui(event.getWhoClicked());
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5§l女")) {
                        gouMain.womangui(event.getWhoClicked());
                    }
                }
            }
        }
    }

    @EventHandler
    public void mangui(InventoryClickEvent event) {
        if (event.getClickedInventory().getName().equals("§6§l确认选择男")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5§l确认")) {
                        Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
                        ConditionBuilder conditionBuilder = new ConditionBuilder(Condition.fieldEquals("player_uuid", player.getUniqueId(),
                                gouPlayerFactory.getInstance().getProfile())).and(Condition.fieldEquals("player_sex", 0, gouPlayerFactory.getInstance().getProfile()));
                        List<gouPlayerModel> models = gouPlayerFactory.getInstance().select(conditionBuilder.build());
                        if (models == null) {
                            return;
                        }
                        if (models.size() < 1) {
                            event.getWhoClicked().sendMessage("§6§l你已选择过性别请使用变性卡");
                            return;
                        }
                        if (models.size() == 1) {
                            gouPlayerModel gouModel = models.get(0);
                            gouModel.playerSex = 1;
                            if (!gouPlayerFactory.getInstance().update(gouModel, "player_sex")) {
                                return;
                            }
                            event.getWhoClicked().sendMessage("性别修改成功");
//修改聊天前缀
                            event.getWhoClicked().closeInventory();
                        }
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5§l取消")) {
                        event.getWhoClicked().closeInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void womangui(InventoryClickEvent event) {
        if (event.getClickedInventory().getName().equals("§6§l确认选择女")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5§l确认")) {
                        Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
                        ConditionBuilder conditionBuilder = new ConditionBuilder(Condition.fieldEquals("player_uuid", player.getUniqueId(),
                                gouPlayerFactory.getInstance().getProfile())).and(Condition.fieldEquals("player_sex", 0, gouPlayerFactory.getInstance().getProfile()));
                        List<gouPlayerModel> models = gouPlayerFactory.getInstance().select(conditionBuilder.build());
                        if (models == null) {
                            return;
                        }
                        if (models.size() < 1) {
                            event.getWhoClicked().sendMessage("§6§l你已选择过性别请使用变性卡");
                            return;
                        }
                        if (models.size() == 1) {
                            gouPlayerModel gouModel = models.get(0);
                            gouModel.playerSex = 2;
                            if (!gouPlayerFactory.getInstance().update(gouModel, "player_sex")) {
                                return;
                            }
                            event.getWhoClicked().sendMessage("性别修改成功");
//修改聊天前缀
                            event.getWhoClicked().closeInventory();
                        }
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5§l取消")) {
                        event.getWhoClicked().closeInventory();
                    }
                }
            }
        }
    }


    @EventHandler
    public void onPlayerChatEvent(PlayerChatEvent event) {
        Player player = event.getPlayer();

        ConditionBuilder conditionBuilder = new ConditionBuilder(Condition.fieldEquals("player_uuid", player.getUniqueId(),
                gouPlayerFactory.getInstance().getProfile())).and(Condition.notEqual("player_sex", 0, gouPlayerFactory.getInstance().getProfile()));
        List<gouPlayerModel> models = gouPlayerFactory.getInstance().select(conditionBuilder.build());
        if (models == null) {
            return;
        }
        if (models.size() < 1) {
            return;
        }
        if (models.size() == 1) {
            gouPlayerModel gouModel = models.get(0);
            if (gouModel.playerMarry == 0) {
                marry = "§4未婚";
            } else if (gouModel.playerMarry == 1) {
                marry = "§6已婚";
            }
            if (gouModel.playerSex == 1) {
                String format = event.getFormat();
                prefix = "男";
                event.setFormat("[" + ChatColor.GOLD + prefix + ChatColor.WHITE + "]" + "[" + marry + ChatColor.WHITE + "]");
                String pformat = event.getFormat();
                event.setFormat(pformat + format);
            } else if (gouModel.playerSex == 2) {
                String format = event.getFormat();
                prefix = "女";
                event.setFormat("[" + ChatColor.RED + prefix + ChatColor.WHITE + "]" + "[" + marry + ChatColor.WHITE + "]");
                String pformat = event.getFormat();
                event.setFormat(pformat + format);
            }
        }
    }
}
