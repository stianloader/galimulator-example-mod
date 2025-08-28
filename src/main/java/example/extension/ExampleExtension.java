package example.extension;

import de.geolykt.starloader.api.event.EventManager;
import de.geolykt.starloader.api.event.lifecycle.SignalExtensionTerminationEvent;
import de.geolykt.starloader.mod.Extension;

public class ExampleExtension extends Extension {

    @Override
    public void initialize() {
        this.getLogger().info("Hello from the example plugin!");
        EventManager.registerListener(new ExampleListener());
    }

    @Override
    public void terminate() {
        // Please be very careful when implementing the terminate function
        // If you are not careful and a deadlock occurs, then the VM might deadlock too
        // As such, do not rely on any other threads in the terminate function and code everything async-safe

        // Instead use SLAPI's ApplicationStopEvent which fires most of the time.

        // The next line is optional, but it signals to others that your extension is soon no longer available.
        // Note that SLAPI calls this event in the unload() method, however we cannot do that as by that time
        // SLAPI (which handles the event) would be unavailable at that point in time. It's a unique thing
        // that works out for SLAPI and SLAPI only.
        EventManager.handleEvent(new SignalExtensionTerminationEvent(this));
    }
}
