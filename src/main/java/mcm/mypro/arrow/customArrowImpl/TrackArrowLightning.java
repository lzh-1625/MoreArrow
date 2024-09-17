package mcm.mypro.arrow.customArrowImpl;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class TrackArrowLightning extends TrackArrow {

    public TrackArrowLightning(Arrow arrow, Player targetPlayer) {
        super(arrow, targetPlayer);
    }

    @Override
    public Boolean handle() {
        if (super.handle()) {
            return true;
        }
        if (arrow.getTicksLived() < 5 || arrow.getTicksLived() % 4 != 1) {
            return false;
        }

        Location location = arrow.getLocation();
        location.setY(arrow.getWorld().getHighestBlockYAt(location));
        arrow.getWorld().strikeLightning(location);
        return false;
    }
}