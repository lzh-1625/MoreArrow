package mcm.mypro.bow.customBowImpl;

import mcm.mypro.arrow.customArrowImpl.LightningArrow;
import mcm.mypro.arrow.customArrowImpl.TrackArrowFire;
import mcm.mypro.bow.CustomBow;
import mcm.mypro.global.ArrowData;
import mcm.mypro.utils.NameSpace;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class LightningBow implements CustomBow {
    @Override
    public String getDisplayName() {
        return "§6弓(引雷)";
    }

    @Override
    public void shootArrow(Arrow arrow, Player player) {
        ArrowData.tagArrowList.add(new LightningArrow(arrow));
    }

    @Override
    public void leftClick(Player player) {

    }
}
