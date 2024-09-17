package mcm.mypro.bow.customBowImpl;

import mcm.mypro.arrow.customArrowImpl.TrackArrow;
import mcm.mypro.bow.CustomBow;
import mcm.mypro.bow.CustomBowHandler;
import mcm.mypro.consts.Eum;
import mcm.mypro.global.ArrowData;
import mcm.mypro.utils.NameSpace;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class TraceBow implements CustomBow {
    @Override
    public String getDisplayName() {
        return "§6弓(追踪)";
    }

    @Override
    public void shootArrow(Arrow arrow, Player player) {
        String targetName;
        try {
            targetName = Objects.requireNonNull(Objects.requireNonNull(player.getPlayer()).getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(NameSpace.traceTarget, PersistentDataType.STRING);
            if (targetName == null || targetName.isEmpty()) {
                return;
            }
        } catch (NullPointerException e) {
            return;
        }
        ArrowData.tagArrowList.add(new TrackArrow(arrow, Bukkit.getPlayer(targetName)));
    }


    @Override
    public void leftClick(Player player) {
        Player targetPlayer = CustomBowHandler.getRayTracePlayer(player);
        if (targetPlayer != null) {
            // 获取主手物品
            ItemStack mainHandItem = player.getInventory().getItemInMainHand();
            if (mainHandItem.hasItemMeta()) {
                ItemMeta itemMeta = mainHandItem.getItemMeta();
                assert itemMeta != null;
                itemMeta.getPersistentDataContainer().set(NameSpace.traceTarget, PersistentDataType.STRING, targetPlayer.getName());
                mainHandItem.setItemMeta(itemMeta);
                player.sendMessage("已将目标设定为 " + targetPlayer.getName());
            }
        }
    }



}
