package mcm.mypro.bow.customBowImpl;

import mcm.mypro.arrow.customArrowImpl.TraceArrowIce;
import mcm.mypro.arrow.customArrowImpl.TrackArrowFire;
import mcm.mypro.bow.CustomBow;
import mcm.mypro.global.ArrowData;
import mcm.mypro.utils.NameSpace;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class TraceIceBow extends TraceBow implements CustomBow {
    @Override
    public String getDisplayName() {
        return "§6弓(追踪&冻结)";
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
        ArrowData.tagArrowList.add(new TraceArrowIce(arrow, Bukkit.getPlayer(targetName)));
    }
}
