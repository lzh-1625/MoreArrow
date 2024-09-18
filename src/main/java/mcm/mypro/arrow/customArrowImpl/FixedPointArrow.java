package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.arrow.CustomArrow;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.util.Vector;

public class FixedPointArrow extends CustomArrowBase implements CustomArrow {
    Integer x;
    Integer z;

    boolean fixedPointOver;

    public FixedPointArrow(Arrow arrow, Integer x, Integer z) {
        this.arrow = arrow;
        this.x = x;
        this.z = z;
    }

    @Override
    public Boolean handle() {
        if (super.handle()) {
            return true;
        }
        if (fixedPointOver) {
            return true;
        }
        if (arrow.getLocation().getX() - x < 0.5 && arrow.getLocation().getX() - x > -0.5 && arrow.getLocation().getZ() - z < 0.5 && arrow.getLocation().getZ() - z > -0.5) {
            arrow.setVelocity(new Vector(0, -1, 0));
            fixedPointOver = true;
            return true;
        }
        Vector startVector = arrow.getVelocity();

        Vector targetVector = TrackArrow.getVector(arrow, new Location(arrow.getWorld(), (double) x, arrow.getWorld().getHighestBlockAt(x, z).getY() + 30, (double) z));
        Vector newVector = new Vector();
        newVector.setX((targetVector.getX() + startVector.getX()) / 2);
        newVector.setY((targetVector.getY() + startVector.getY()) / 2);
        newVector.setZ((targetVector.getZ() + startVector.getZ()) / 2);
        arrow.setVelocity(newVector);
        return false;
    }

}
