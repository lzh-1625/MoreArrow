package mcm.mypro.bow.customBowImpl;

import mcm.mypro.arrow.customArrowImpl.TrackArrow;
import mcm.mypro.arrow.customArrowImpl.TrackArrowLightning;
import mcm.mypro.bow.CustomBow;
import mcm.mypro.bow.CustomBowHandler;
import mcm.mypro.global.ArrowData;
import mcm.mypro.utils.NameSpace;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class TraceLightningBow extends TraceBow implements CustomBow {
    @Override
    public String getDisplayName() {
        return "§6弓(追踪&引雷)";
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
        arrow.setGravity(false);
        ArrowData.customArrowList.add(new TrackArrowLightning(arrow, Bukkit.getPlayer(targetName)));
    }


}
