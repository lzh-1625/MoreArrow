package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.consts.Consts;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class TrackArrowFire extends TrackArrow {
    public TrackArrowFire(Arrow arrow, Player targetPlayer) {
        super(arrow, targetPlayer);
    }

    public TrackArrowFire(Arrow arrow, Player targetPlayer, Player belongPlayer) {
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
            double compensation = (int) (Consts.FIRE_RANGE / 2);
            for (int x = 0; x < Consts.FIRE_RANGE; x++) {
                for (int z = 0; z < Consts.FIRE_RANGE; z++) {
                    Location location = arrow.getLocation();
                    location.setX(location.getX() - compensation + x);
                    location.setZ(location.getZ() - compensation + z);
                    Block block = arrow.getWorld().getHighestBlockAt(location).getRelative(0, 1, 0);
                    if (block.isEmpty()) {
                        block.setType(Material.FIRE);
                    }
                }
            }
            return true;
        }
        Block block = arrow.getWorld().getHighestBlockAt(arrow.getLocation()).getRelative(0, 1, 0);
        if (block.isEmpty()) {
            block.setType(Material.FIRE);
        }
        return false;
    }

}
