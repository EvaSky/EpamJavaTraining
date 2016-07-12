package by.epam.port.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Warehouse {

    private List<Container> containerList;
    private int size;
    private Lock lock;

    public Warehouse(int size) {
        lock = new ReentrantLock();
        containerList = new ArrayList<Container>(size);
        this.size = size;
    }

    public boolean addContainer(Container container) {
        lock.lock();
        try{
            return containerList.add(container);
        }
        finally {
            lock.unlock();
        }

    }

    public boolean addContainer(List<Container> containers) {
        lock.lock();
        boolean result = false;
        if(containers.size() <= getFreeSize()){
            result = containerList.addAll(containers);
        }
        else {
            lock.unlock();
            return false;
        }
        lock.unlock();
        return result;
    }

    public Container getContainer() {
        lock.lock();
        try {
            if (containerList.size() > 0) {
                return containerList.remove(0);
            }
        }
        finally {
            lock.unlock();
        }
        return null;
    }

    public List<Container> getContainer(int amount) {
        lock.lock();
        if (containerList.size() >= amount) {
            List<Container> cargo = new ArrayList<>(containerList.subList(0, amount));
            containerList.removeAll(cargo);
            lock.unlock();
            return cargo;
        }
        lock.unlock();
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

	public Lock getLock(){
		return lock;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;

        Warehouse warehouse = (Warehouse) o;

        if (size != warehouse.size) return false;
        if (containerList != null ? !containerList.equals(warehouse.containerList) : warehouse.containerList != null)
            return false;
        return !(lock != null ? !lock.equals(warehouse.lock) : warehouse.lock != null);

    }

    @Override
    public int hashCode() {
        int result = containerList != null ? containerList.hashCode() : 0;
        result = 31 * result + size;
        result = 31 * result + (lock != null ? lock.hashCode() : 0);
        return result;
    }
}
