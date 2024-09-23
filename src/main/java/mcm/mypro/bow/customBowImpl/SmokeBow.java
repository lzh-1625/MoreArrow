package mcm.mypro.bow.customBowImpl;

import mcm.mypro.arrow.customArrowImpl.PersistentArrow;
import mcm.mypro.arrow.customArrowImpl.TntArrow;
import mcm.mypro.bow.CustomBow;
import mcm.mypro.global.ArrowData;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class SmokeBow implements CustomBow {
    @Override
    public String getDisplayName() {
        return "§6弓(烟雾)";
    }

    @Override
    public void shootArrow(Arrow arrow, Player player) {
        arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
        ArrowData.customArrowList.add(new PersistentArrow(arrow,player));
    }

    @Override
    public void leftClick(Player player) {

    }
}
