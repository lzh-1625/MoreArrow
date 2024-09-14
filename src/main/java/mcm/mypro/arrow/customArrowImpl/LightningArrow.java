package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.arrow.CustomArrow;
import mcm.mypro.consts.Consts;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;

public class LightningArrow extends CustomArrowBase implements CustomArrow {
    public LightningArrow(Arrow arrow) {
        this.arrow = arrow;
    }


    @Override
    public Boolean handle() {
        if (super.handle()) {
            return true;
        }
        if (arrow.isDead() || arrow.isOnGround() || !arrow.isValid()) {
            rangeLightning(arrow);
            return true;
        }
        return false;
    }

    public static void rangeLightning(Arrow arrow) {
        double compensation = (int) (Consts.LIGHTNING_RANGE / 2);
        for (int x = 0; x < Consts.LIGHTNING_RANGE; x++) {
            for (int z = 0; z < Consts.LIGHTNING_RANGE; z++) {
                Location location = arrow.getLocation();
                location.setX(location.getX() - compensation + x);
                location.setZ(location.getZ() - compensation + z);
                location.setY(arrow.getWorld().getHighestBlockYAt(location));
                arrow.getWorld().strikeLightning(location);
            }
        }
    }
}
