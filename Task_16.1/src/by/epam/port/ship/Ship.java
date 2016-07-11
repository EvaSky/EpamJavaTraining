package by.epam.port.ship;

import by.epam.port.exception.PortException;
import by.epam.port.port.Berth;
import by.epam.port.port.Port;

import by.epam.port.warehouse.Container;
import by.epam.port.warehouse.Warehouse;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;

/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Ship implements Runnable{

    private final static Logger logger = Logger.getRootLogger();
    private volatile boolean stopShip = false;

    private String name;
    private Port port;
    private Warehouse shipWarehouse;

    public Ship() {
    }

    public Ship(String name, Port port, int shipWarehouseSize) {
        this.name = name;
        this.port = port;
        this.shipWarehouse = new Warehouse(shipWarehouseSize);
    }


    public void setContainersToWarehouse(List<Container> containerList) {
        shipWarehouse.addContainer(containerList);
    }

    public void stopShip() {
        stopShip = true;
    }

    public void run() {
        try {
            while (!stopShip) {
                atSea();
                inPort();
            }
        } catch (InterruptedException e) {
            logger.error("С кораблем случилась неприятность и он уничтожен.", e);
        } catch (PortException e) {
            logger.error("Корабль не нашел причал", e);
        }
    }

    private void atSea() throws InterruptedException {
        Thread.sleep(1000);
    }


    private void inPort() throws PortException, InterruptedException {
        boolean isLockedBerth = false;
        Berth berth = null;
        try {
            isLockedBerth = port.lockBerth(this);

            if (isLockedBerth) {
                berth = port.getBerth(this);
                ShipAction action = getNextAction();
                executeAction(action, berth);
            } else {
                logger.info("Кораблю " + name + " отказано в швартовке к причалу ");
            }
        } finally {
            if (isLockedBerth) {
                port.unlockBerth(this);
            }
        }
    }

    private void executeAction(ShipAction action, Berth berth) throws InterruptedException {
        switch (action) {
            case LOAD_TO_PORT:
                loadToPort(berth);
                break;
            case LOAD_FROM_PORT:
                loadFromPort(berth);
                break;
        }
    }

    private boolean loadToPort(Berth berth) throws InterruptedException {

        int containersNumberToMove = conteinersCount(shipWarehouse.getRealSize());
        boolean result = false;

        logger.debug("Корабль " + name + " хочет загрузить " + containersNumberToMove
                + " контейнеров на склад порта.");

        result = berth.add(shipWarehouse, containersNumberToMove);

        if (!result) {
            logger.debug("Недостаточно места на складе порта для выгрузки кораблем "
                    + name + " " + containersNumberToMove + " контейнеров.");
        } else {
            logger.debug("Корабль " + name + " выгрузил " + containersNumberToMove
                    + " контейнеров в порт.");

        }
        return result;
    }

    private boolean loadFromPort(Berth berth) throws InterruptedException {

        int containersNumberToMove = conteinersCount(shipWarehouse.getFreeSize());
        boolean result = false;
        logger.debug("Корабль " + name + " хочет загрузить " + containersNumberToMove
                + " контейнеров со склада порта.");
        result = berth.get(shipWarehouse, containersNumberToMove);

        if (result) {
            logger.debug("Корабль " + name + " загрузил " + containersNumberToMove
                    + " контейнеров из порта.");
        } else {
            logger.debug("Недостаточно места на на корабле " + name
                    + " для погрузки " + containersNumberToMove + " контейнеров из порта.");
        }

        return result;
    }

    private int conteinersCount(int maxCount) {
        Random random = new Random();
        return random.nextInt(maxCount);
    }

    private ShipAction getNextAction() {
        Random random = new Random();
        int value = random.nextInt(4000);
        if (value < 2000) {
            return ShipAction.LOAD_TO_PORT;
        } else {
            return ShipAction.LOAD_FROM_PORT;
        }
    }


    enum ShipAction {
        LOAD_TO_PORT, LOAD_FROM_PORT
    }
}
