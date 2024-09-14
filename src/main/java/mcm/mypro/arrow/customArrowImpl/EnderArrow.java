package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.arrow.CustomArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class EnderArrow extends CustomArrowBase implements CustomArrow {
    public EnderArrow(Arrow arrow, Player belongPlayer) {
        this.arrow = arrow;
        this.belongPlayer = belongPlayer;
    }

    @Override
    public Boolean handle() {
        if (super.handle()) {
            return true;
        }
        if (arrow.isDead() || arrow.isOnGround() || !arrow.isValid()) {
            belongPlayer.teleport(arrow);
            return true;
        }
        return false;
    }
}
