package pl.sparkidea.springactuatorstatus.service;

import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class FlipWorkingStatusService {

    private final Lock lock = new ReentrantLock();
    private final Status[] statusesChain = new Status[]{
            Status.UP,
            Status.OUT_OF_SERVICE,
            Status.DOWN,
            Status.UNKNOWN
    };
    private int statusIndex = 0;

    public Status toggleFlip() {
        lock.lock();
        try {
            statusIndex += 1;
            return statusesChain[statusIndex % statusesChain.length];
        } finally {
            lock.unlock();
        }
    }

    public Status getCurrentStatus() {
        lock.lock();
        try {
            return statusesChain[statusIndex];
        } finally {
            lock.unlock();
        }
    }

    public int getCurrentIndex() {
        lock.lock();
        try {
            return statusIndex;
        } finally {
            lock.unlock();
        }
    }

    public Status[] getStatusesChain() {
        return statusesChain;
    }

}
