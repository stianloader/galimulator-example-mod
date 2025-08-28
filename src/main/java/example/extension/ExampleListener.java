package example.extension;

import de.geolykt.starloader.api.Galimulator;
import de.geolykt.starloader.api.dimension.Empire;
import de.geolykt.starloader.api.event.EventHandler;
import de.geolykt.starloader.api.event.EventPriority;
import de.geolykt.starloader.api.event.Listener;
import de.geolykt.starloader.api.event.alliance.AllianceJoinEvent;
import de.geolykt.starloader.api.gui.BasicDialogBuilder;

public class ExampleListener implements Listener {

    @EventHandler(EventPriority.MONITOR)
    public void onAllianceJoin(AllianceJoinEvent event) {
        if (event.getAlliance().getMemberView().size() > 2) { // These are not the founders
            Empire playerEmpire = Galimulator.getUniverse().getPlayerEmpire();
            if (playerEmpire != null && event.getJoiningEmpire() != playerEmpire) { // Not the player empire
                if (event.getAlliance().hasEmpire(playerEmpire)) { // The player is in the alliance
                    BasicDialogBuilder dialogBuilder = new BasicDialogBuilder("Empire joins Alliance", event.getJoiningEmpire().getEmpireName() + " has joined your alliance.");
                    dialogBuilder.setDuration(3); // Only show for 3 seconds maximum
                    dialogBuilder.show(); // Show dialog
                }
            }
        }
    }
}
