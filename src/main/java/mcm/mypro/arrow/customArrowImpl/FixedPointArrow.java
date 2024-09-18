package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.arrow.CustomArrow;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.util.Vector;

public class FixedPointArrow extends CustomArrowBase implements CustomArrow {
    double x;
    double z;

    boolean fixedPointOver;

    public FixedPointArrow(Arrow arrow, Integer x, Integer z) {
        this.arrow = arrow;
        this.x = x;
        this.z = z;
    }

    @Override
    public Boolean handle() {
        if (super.handle() || fixedPointOver) {
            return true;
        }
        if (arrow.getTicksLived() % interval != 0) {
            return false;
        }
        if (arrow.getLocation().getX() - x < 0.5 && arrow.getLocation().getX() - x > -0.5 && arrow.getLocation().getZ() - z < 0.5 && arrow.getLocation().getZ() - z > -0.5) {
            arrow.setVelocity(new Vector(0, -1, 0));
            fixedPointOver = true;
            arrow.setGravity(true);
            return true;
        }
        Vector startVector = arrow.getVelocity();

        Vector targetVector = TrackArrow.getVector(arrow, new Location(arrow.getWorld(), x, arrow.getWorld().getHighestBlockAt((int) x, (int) z).getY() + 30, z));
        Vector newVector = new Vector();
        newVector.setX((targetVector.getX() + startVector.getX()) / 2);
        newVector.setY((targetVector.getY() + startVector.getY()) / 2);
        newVector.setZ((targetVector.getZ() + startVector.getZ()) / 2);
        arrow.setVelocity(newVector);
        return false;
    }

}
