/*
 * MIT License
 *
 * Copyright (c) 2018 Bonosoft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package bonosoft.schedule;

import bonosoft.schedule.scheduler.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * by bn on 02/12/2018.
 */
public class Schedule {
    private int workers = 0;

    ArrayList<IScheduleEvent> iScheduleEvents = new ArrayList<>();
    private LinkedBlockingQueue<IScheduleEvent> eventQueue = new LinkedBlockingQueue<>();


    public void minuteTick(ScheduleTime scheduleTime) {
        for(IScheduleEvent iScheduleEvent : iScheduleEvents) {
            if (iScheduleEvent.getTrigger().minuteCheck(scheduleTime)) {
                eventQueue.offer(iScheduleEvent);
            }
        }
    }

    public void hourTick(ScheduleTime scheduleTime) {
        for(IScheduleEvent iScheduleEvent : iScheduleEvents) {
            if (iScheduleEvent.getTrigger().hourCheck(scheduleTime)) {
                eventQueue.offer(iScheduleEvent);
            }
        }
    }

    public void dayTick(ScheduleTime scheduleTime) {
        for(IScheduleEvent iScheduleEvent : iScheduleEvents) {
            if (iScheduleEvent.getTrigger().dayCheck(scheduleTime)) {
                eventQueue.offer(iScheduleEvent);
            }
        }
    }

    public void monthTick(ScheduleTime scheduleTime) {
        for(IScheduleEvent iScheduleEvent : iScheduleEvents) {
            if (iScheduleEvent.getTrigger().monthCheck(scheduleTime)) {
                eventQueue.offer(iScheduleEvent);
            }
        }
    }

    public void yearTick(ScheduleTime scheduleTime) {
        for(IScheduleEvent iScheduleEvent : iScheduleEvents) {
            if (iScheduleEvent.getTrigger().yearCheck(scheduleTime)) {
                eventQueue.offer(iScheduleEvent);
            }
        }
    }

    public IScheduleEvent getEvent() throws InterruptedException {
        return eventQueue.take();
    }

    public void start(int workers) {
        this.workers = workers;
        for(int i=0; i<workers; i++) {
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            workerThreads.add(workerThread);
        }
    }

    public void close() {
        eventQueue.clear();
        for(int i=0; i<=workers; i++) {
            eventQueue.offer(new StopEvent());
        }
        scheduleThread.close();
    }

    public ArrayList<WorkerStatus> getStatuses() {
        ArrayList<WorkerStatus> statuses = new ArrayList<>();
        for(WorkerThread workerThread : workerThreads) {
            statuses.add(workerThread.getStatus());
        }
        return statuses;
    }

    private ScheduleThread scheduleThread;
    private List<WorkerThread> workerThreads = new ArrayList<>();

    public static Schedule getInstance() { return log; }
    private static Schedule log = new Schedule();
    private Schedule() {
        scheduleThread = new ScheduleThread();
        scheduleThread.start();
    }

}
