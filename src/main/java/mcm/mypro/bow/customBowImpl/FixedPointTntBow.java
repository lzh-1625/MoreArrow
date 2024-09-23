package mcm.mypro.bow.customBowImpl;

import mcm.mypro.arrow.customArrowImpl.FixedPointArrow;
import mcm.mypro.arrow.customArrowImpl.FixedPointArrowTnt;
import mcm.mypro.global.ArrowData;
import mcm.mypro.utils.NameSpace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class FixedPointTntBow extends FixedPointBow {
    @Override
    public String getDisplayName() {
        return "§6弓(定点&爆炸)";
    }

    @Override
    public void shootArrow(Arrow arrow, Player player) {
        try {
            String coordinateStr = Objects.requireNonNull(Objects.requireNonNull(player.getPlayer()).getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(NameSpace.fixedPoint, PersistentDataType.STRING);
            assert coordinateStr != null;
            String[] coordinateList = coordinateStr.split("\\|");
            arrow.setGravity(false);
            ArrowData.customArrowList.add(new FixedPointArrowTnt(arrow, Integer.parseInt(coordinateList[0]), Integer.parseInt(coordinateList[1])));
        } catch (Exception ignored) {

        }

    }

}
