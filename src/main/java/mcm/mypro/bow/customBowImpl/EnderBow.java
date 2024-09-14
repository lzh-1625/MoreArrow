package mcm.mypro.bow.customBowImpl;

import mcm.mypro.arrow.customArrowImpl.EnderArrow;
import mcm.mypro.bow.CustomBow;
import mcm.mypro.global.ArrowData;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class EnderBow implements CustomBow {
    @Override
    public String getDisplayName() {
        return "§6弓(末影)";
    }

    @Override
    public void shootArrow(Arrow arrow, Player player) {
        ArrowData.tagArrowList.add(new EnderArrow(arrow, player));
    }


    @Override
    public void leftClick(Player player) {

    }

}
