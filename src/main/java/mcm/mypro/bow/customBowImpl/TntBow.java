package mcm.mypro.bow.customBowImpl;

import mcm.mypro.arrow.customArrowImpl.TntArrow;
import mcm.mypro.bow.CustomBow;
import mcm.mypro.global.ArrowData;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class TntBow implements CustomBow {
    @Override
    public String getDisplayName() {
        return "§6弓(爆炸)";
    }

    @Override
    public void shootArrow(Arrow arrow, Player player) {
        ArrowData.customArrowList.add(new TntArrow(arrow, player));
    }

    @Override
    public void leftClick(Player player) {

    }
}
