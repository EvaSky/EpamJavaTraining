package by.epam.port.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Warehouse {

    private List<Container> containerList;
    private int size;

    public Warehouse(int size) {
        containerList = new ArrayList<>(size);
        this.size = size;
    }

    public boolean addContainer(Container container) {
        return containerList.add(container);
    }

    public boolean addContainer(List<Container> containers) {
        boolean result = false;
        if(containers.size() <= getFreeSize()){
            result = containerList.addAll(containers);
        }
        else {
            return false;
        }
        return result;
    }

    public Container getContainer() {
        if (containerList.size() > 0) {
            return containerList.remove(0);
        }
        return null;
    }

    public List<Container> getContainer(int amount) {
        if (containerList.size() >= amount) {
            List<Container> cargo = new ArrayList<>(containerList.subList(0, amount));
            containerList.removeAll(cargo);
            return cargo;
        }
        return null;
    }

    public int getSize(){
        return size;
    }

    public int getRealSize(){
        return containerList.size();
    }

    public int getFreeSize(){
        return size - containerList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return size == warehouse.size &&
                Objects.equals(containerList, warehouse.containerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(containerList, size);
    }
}
