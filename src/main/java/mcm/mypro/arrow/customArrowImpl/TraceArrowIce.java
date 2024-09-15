package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.consts.Consts;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class TraceArrowIce extends TrackArrow {
    public TraceArrowIce(Arrow arrow, Player targetPlayer) {
        super(arrow, targetPlayer);
    }

    public TraceArrowIce(Arrow arrow, Player targetPlayer, Player belongPlayer) {
        super(arrow, targetPlayer, belongPlayer);
    }

    @Override
    public Boolean handle() {
        if (super.handle()) {
            return true;
        }
        if (arrow.getTicksLived() < 5) {
            return false;
        }
        if (arrow.isDead() || arrow.isOnGround() || !arrow.isValid()) {
            IceArrow.rangeIce(arrow);
            return true;
        }
        Block block = arrow.getWorld().getHighestBlockAt(arrow.getLocation());
        if (block.getType().equals(Material.WATER)) {
            block.setType(Material.ICE);
        }
        return false;
    }
}
