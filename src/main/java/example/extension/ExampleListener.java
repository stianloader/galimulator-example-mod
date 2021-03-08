package example.extension;

import de.geolykt.starloader.api.Galimulator;
import de.geolykt.starloader.api.event.EventHandler;
import de.geolykt.starloader.api.event.EventPriority;
import de.geolykt.starloader.api.event.Listener;
import de.geolykt.starloader.api.event.alliance.AllianceJoinEvent;
import de.geolykt.starloader.api.gui.BasicDialogBuilder;

public class ExampleListener implements Listener {

    @EventHandler(EventPriority.MONITOR)
    public void onAllianceJoin(AllianceJoinEvent event) {
        // I haven't actually tested that it really works, but it'd be surprised if it didn't
        // So if there is an issue with this, feel free to file a bug report
        if (event.getAlliance().getMembers().size() > 2) { // These are not the founders
            if (event.getEmpire() != Galimulator.getPlayerEmpire()) { // Not the player empire
                if (event.getAlliance().hasEmpire(Galimulator.getPlayerEmpire())) { // The player is in the alliance
                    BasicDialogBuilder dialogBuilder = new BasicDialogBuilder("Empire joins Alliance", event.getEmpire().getEmpireName() + " has joined your alliance.");
                    dialogBuilder.setDuration(3); // Only show for 3 seconds maximum
                    dialogBuilder.buildAndShow(); // Show dialog
                }
            }
        }
    }
}
