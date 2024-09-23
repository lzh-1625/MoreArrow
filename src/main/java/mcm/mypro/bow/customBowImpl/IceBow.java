package mcm.mypro.bow.customBowImpl;

import mcm.mypro.arrow.customArrowImpl.IceArrow;
import mcm.mypro.bow.CustomBow;
import mcm.mypro.global.ArrowData;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class IceBow implements CustomBow {
    @Override
    public String getDisplayName() {
        return "§6弓(冻结)";
    }

    @Override
    public void shootArrow(Arrow arrow, Player player) {
        ArrowData.customArrowList.add(new IceArrow(arrow));
    }

    @Override
    public void leftClick(Player player) {

    }
}
