package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.arrow.CustomArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.util.Vector;

public class FixedPointArrowTnt extends FixedPointArrow implements CustomArrow {
    public FixedPointArrowTnt(Arrow arrow, Integer x, Integer z) {
        super(arrow, x, z);
    }

    @Override
    public Boolean handle() {
        if (super.handle()) {
            if (arrow.isDead() || arrow.isOnGround() || !arrow.isValid()) {
                TntArrow.explosion(arrow, belongPlayer);
                return true;
            }
        }
        return false;
    }
}
