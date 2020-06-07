package me.nurio.bungeekeeper.server.management;

/**
 * EventCallers calls events and manages post event actions.
 */
public interface EventCaller {

    /**
     * Call the event, wait for listeners and perform finally actions.
     */
    void call();

}