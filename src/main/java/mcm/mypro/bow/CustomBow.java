package mcm.mypro.bow;

import mcm.mypro.consts.Eum;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public interface CustomBow {
    String getDisplayName();

    void shootArrow(Arrow arrow, Player player);

    void leftClick(Player player);

}
