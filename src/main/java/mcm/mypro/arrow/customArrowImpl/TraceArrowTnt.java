package mcm.mypro.arrow.customArrowImpl;


import mcm.mypro.arrow.CustomArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class TraceArrowTnt extends TrackArrow implements CustomArrow {
    public TraceArrowTnt(Arrow arrow, Player targetPlayer) {
        super(arrow, targetPlayer);
    }

    public TraceArrowTnt(Arrow arrow, Player targetPlayer, Player belongPlayer) {
        super(arrow, targetPlayer, belongPlayer);
    }

    @Override
    public Boolean handle() {
        if (super.handle()) {
            TntArrow.explosion(arrow, belongPlayer);
            return true;
        }
        return false;
    }
}
