package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.arrow.CustomArrow;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class TrackArrow extends CustomArrowBase implements CustomArrow {

    private final Player targetPlayer;

    public TrackArrow(Arrow arrow, Player targetPlayer) {
        this.targetPlayer = targetPlayer;
        this.arrow = arrow;
    }

    public TrackArrow(Arrow arrow, Player targetPlayer, Player belongPlayer) {
        this.targetPlayer = targetPlayer;
        this.arrow = arrow;
        this.belongPlayer = belongPlayer;
    }

    @Override
    public Boolean handle() {
        if (!targetPlayer.isOnline()) {
            return true;
        }
        if (arrow.isDead() || arrow.isOnGround() || !arrow.isValid()) {
            return true;
        }
        if (arrow.getTicksLived() < 5 || arrow.getTicksLived() % interval != 0) {
            return false;
        }
        Vector startVector = arrow.getVelocity();
        Vector targetVector = getVector(arrow, targetPlayer);
        Vector newVector = new Vector();
        newVector.setX((targetVector.getX() + startVector.getX()) / 2);
        newVector.setY((targetVector.getY() + startVector.getY()) / 2);
        newVector.setZ((targetVector.getZ() + startVector.getZ()) / 2);
        arrow.setVelocity(newVector);
        return false;
    }


    @NotNull
    private static Vector getVector(Arrow arrow, Player targetPlayer) {
        Location startLocation = arrow.getLocation();
        Location targetLocation = targetPlayer.getLocation();
        Vector newVector = new Vector();

        double deltaX = targetLocation.getX() - startLocation.getX();
        double deltaY = targetLocation.getY() - startLocation.getY();
        double deltaZ = targetLocation.getZ() - startLocation.getZ();

        double sum = (deltaX > 0 ? deltaX : -deltaX) + (deltaY > 0 ? deltaY : -deltaY) + (deltaZ > 0 ? deltaZ : -deltaZ);

        newVector.setX(deltaX / sum);
        newVector.setY(deltaY / sum);
        newVector.setZ(deltaZ / sum);
        return newVector;
    }
}

