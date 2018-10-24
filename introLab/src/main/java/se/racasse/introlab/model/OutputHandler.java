package se.racasse.introlab.model;

/**
 * Handles broadcast messages from server.
 */
public interface OutputHandler {
    /**
     * Called when a notifying view with results from database.
     *
     * @param msg The message from the server.
     */
    public void handleMsg(String msg);
}
