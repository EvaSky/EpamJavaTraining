package by.epam.port.start;

import by.epam.port.port.Port;
import by.epam.port.ship.Ship;
import by.epam.port.warehouse.Container;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Main {
    private static final int WAREHOUSE_PORTSIZE = 15;//заполненность хранилища порта
    private static final int BERTH_NUM = 2; //количество причалов
    private static final int WAREHOUSE_PORT_CAPACITY = 900;//общая вместимость склада порта
    private static final int SHIP_WAREHOUSE_SIZE = 90; //вместимость трюма корабля

    public static void main(String[] args) throws InterruptedException {


        List<Container> containerList = new ArrayList<>(WAREHOUSE_PORTSIZE);
        for (int i=0; i < WAREHOUSE_PORTSIZE; i++){
            containerList.add(new Container(i));
        }

        Port port = new Port(BERTH_NUM, WAREHOUSE_PORT_CAPACITY);//Порт - 2 причала, вместимость хранилища - 900
        port.setContainersToWarehouse(containerList);


        containerList = new ArrayList<>(WAREHOUSE_PORTSIZE);
        for (int i=0; i < WAREHOUSE_PORTSIZE; i++){
            containerList.add(new Container(i+30));
        }
        Ship ship1 = new Ship("Ship1", port, SHIP_WAREHOUSE_SIZE);
        ship1.setContainersToWarehouse(containerList);

        containerList = new ArrayList<>(WAREHOUSE_PORTSIZE);
        for (int i=0; i < WAREHOUSE_PORTSIZE; i++){
            containerList.add(new Container(i+60));
        }
        Ship ship2 = new Ship("Ship2", port, SHIP_WAREHOUSE_SIZE);
        ship2.setContainersToWarehouse(containerList);

        containerList = new ArrayList<>(WAREHOUSE_PORTSIZE);
        for (int i=0; i < WAREHOUSE_PORTSIZE; i++){
            containerList.add(new Container(i+90));
        }
        Ship ship3 = new Ship("Ship3", port, SHIP_WAREHOUSE_SIZE);
        ship3.setContainersToWarehouse(containerList);


        new Thread(ship1).start();
        new Thread(ship2).start();
        new Thread(ship3).start();


        Thread.sleep(3000);

        ship1.stopShip();
        ship2.stopShip();
        ship3.stopShip();

    }
}
