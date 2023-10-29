package jcource.battleship.gameCore.ships;

public class ShipContainerBuilder {
    public ShipContainerBuilder() {
    }

    private final ShipContainer shipContainer = new ShipContainer();

    public ShipContainerBuilder addOneDeckShip(OneDeckShip oneDeckShip) throws IllegalStateException {
        shipContainer.addOneDeckShip(oneDeckShip);
        return this;
    }

    public ShipContainerBuilder addTwoDeckShip(TwoDeckShip twoDeckShip) throws IllegalStateException {
        shipContainer.addTwoDeckShip(twoDeckShip);
        return this;
    }

    public ShipContainerBuilder addThreeDeckShip(ThreeDeckShip threeDeckShip) throws IllegalStateException {
        shipContainer.addThreeDeckShip(threeDeckShip);
        return this;
    }

    public ShipContainerBuilder addFourDeckShip(FourDeckShip fourDeckShip) throws IllegalStateException {
        shipContainer.addFourDeckShip(fourDeckShip);
        return this;
    }

    public ShipContainer build() throws IllegalStateException {
        if (shipContainer.isComplete()) return shipContainer;
        else throw new IllegalStateException("The container is not accomplished");
    }
}
