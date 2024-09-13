package mcm.mypro.arrow.CustomArrowImpl;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class TrackArrowFire extends TrackArrow {
    public TrackArrowFire(Arrow arrow, Player targetPlayer) {
        super(arrow, targetPlayer);
    }

    @Override
    public Boolean handle() {
        if (super.handle()) {
            return true;
        }
        if (arrow.getTicksLived() < 5) {
            return false;
        }
        Block block = arrow.getWorld().getHighestBlockAt(arrow.getLocation()).getRelative(0, 1, 0);
        if (block.isEmpty()) {
            block.setType(Material.FIRE);
        }
        return false;
    }

}
