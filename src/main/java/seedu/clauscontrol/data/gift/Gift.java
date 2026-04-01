//@@author prerana-r11
package seedu.clauscontrol.data.gift;

import java.util.logging.Logger;


public class Gift {
    private static final Logger logger = Logger.getLogger(Gift.class.getName());
    private final String giftName;
    private State state;

    public enum State {
        PREPARED,
        IN_PROGRESS,
        DELIVERED
    }


    public Gift(String giftName) {
        assert giftName != null : "Gift name should not be null";
        if (giftName == null || giftName.isBlank()) {
            logger.warning("Attempted to create Gift with invalid name");
            throw new IllegalArgumentException("Gift name cannot be null or empty");
        }

        this.giftName = giftName;
        this.state = State.IN_PROGRESS;
        logger.info("Created gift: " + giftName);
    }

    public String getGiftName() {
        return giftName;
    }
    public boolean isDelivered(){
        return state==State.DELIVERED;
    }
    public State getState(){
        return state;
    }

    public void markPrepared() {
        if (state == State.DELIVERED) {
            throw new IllegalStateException("oops you can't modify a delivered gift :(");
        }
        this.state = State.PREPARED;
    }

    public void markUndelivered() {
        this.state = State.IN_PROGRESS;
    }

    public void markDelivered() {
        this.state = State.DELIVERED;
        logger.info("Gift '" + giftName + "' marked as delivered");
    }

    @Override
    public String toString() {
        switch (state) {
        case PREPARED:
            return "[Prepared]" + giftName;
        case DELIVERED:
            return "[Delivered] " + giftName;
        default:
            return "[In progress] " + giftName;

        }
    }
}

//@@author
