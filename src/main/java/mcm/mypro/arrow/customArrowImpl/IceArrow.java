package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.arrow.CustomArrow;
import mcm.mypro.consts.Consts;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;

public class IceArrow extends CustomArrowBase implements CustomArrow {
    public IceArrow(Arrow arrow) {
        this.arrow = arrow;
    }

    @Override
    public Boolean handle() {
        if (super.handle()) {
            return true;
        }
        if (arrow.isDead() || arrow.isOnGround() || !arrow.isValid()) {
            rangeIce(arrow);
            return true;
        }
        return false;
    }

    public static void rangeIce(Arrow arrow) {
        double compensation = (int) (Consts.ICE_RANGE / 2);
        for (int x = 0; x < Consts.ICE_RANGE; x++) {
            for (int y = 0; y < Consts.ICE_RANGE; y++) {
                for (int z = 0; z < Consts.ICE_RANGE; z++) {
                    Location location = arrow.getLocation();
                    location.setX(location.getX() - compensation + x);
                    location.setY(location.getY() - compensation + y);
                    location.setZ(location.getZ() - compensation + z);
                    Block block = arrow.getWorld().getBlockAt(location);
                    System.out.println(block.getLocation());
                    if (block.isEmpty()) {
                        block.setType(Material.ICE);
                    }
                }
            }
        }
    }
}
