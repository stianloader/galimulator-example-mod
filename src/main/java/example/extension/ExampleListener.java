package example.extension;

import de.geolykt.starloader.api.Galimulator;
import de.geolykt.starloader.api.empire.ActiveEmpire;
import de.geolykt.starloader.api.event.EventHandler;
import de.geolykt.starloader.api.event.EventPriority;
import de.geolykt.starloader.api.event.Listener;
import de.geolykt.starloader.api.event.alliance.AllianceJoinEvent;
import de.geolykt.starloader.api.gui.BasicDialogBuilder;

public class ExampleListener implements Listener {

    @EventHandler(EventPriority.MONITOR)
    public void onAllianceJoin(AllianceJoinEvent event) {
        if (event.getAlliance().getMembers().size() > 2) { // These are not the founders
            ActiveEmpire playerEmpire = Galimulator.getPlayerEmpire();
            if (playerEmpire != null && event.getEmpire() != playerEmpire) { // Not the player empire
                if (event.getAlliance().hasEmpire(playerEmpire)) { // The player is in the alliance
                    BasicDialogBuilder dialogBuilder = new BasicDialogBuilder("Empire joins Alliance", event.getEmpire().getEmpireName() + " has joined your alliance.");
                    dialogBuilder.setDuration(3); // Only show for 3 seconds maximum
                    dialogBuilder.show(); // Show dialog
                }
            }
        }
    }
}
