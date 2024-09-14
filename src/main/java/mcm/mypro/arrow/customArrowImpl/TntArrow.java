package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.arrow.CustomArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class TntArrow extends CustomArrowBase implements CustomArrow {

    public TntArrow(Arrow arrow, Player belongPlayer) {
        this.arrow = arrow;
        this.belongPlayer = belongPlayer;
    }


    @Override
    public Boolean handle() {
        if (super.handle()) {
            return true;
        }
        if (arrow.isDead() || arrow.isOnGround() || !arrow.isValid()) {
            explosion(arrow, belongPlayer);
            return true;
        }
        return false;
    }

    public static void explosion(Arrow arrow, Player belongPlayer) {
        arrow.getWorld().createExplosion(arrow.getLocation(), 6.0f, true, true, belongPlayer);
    }
}
