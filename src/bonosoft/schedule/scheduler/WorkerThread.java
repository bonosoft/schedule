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

package bonosoft.schedule.scheduler;

import bonosoft.schedule.Schedule;

/**
 * by bn on 02/12/2018.
 */
public class WorkerThread extends Thread {

    private boolean isRunning;
    private boolean isWaiting;
    private long startTime;
    private long lastRunLength;

    @Override
    public void run() {
        isRunning = true;
        isWaiting = false;
        try {
            IScheduleEvent event;
            do {
                isWaiting = true;
                event = Schedule.getInstance().getEvent();
                isWaiting = false;

                startTime = System.currentTimeMillis();

                if (event!=null && event.getTrigger()!=null) {
                    event.doEvent();
                    lastRunLength = System.currentTimeMillis() - startTime;
                }
            } while (event != null && event.getTrigger() != null);
        } catch (InterruptedException ie) {
            System.out.println("Closing scgeduler event listener, because interrupt signal was received.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            isRunning = false;
        }
    }

    public WorkerStatus getStatus() {
        WorkerStatus status = new WorkerStatus();
        status.isRunning = isRunning;
        status.lastRunLength = lastRunLength;
        status.isWaiting = isWaiting;
        status.isWorking = !status.isWaiting;
        status.startTime = (status.isWaiting) ? 0 : startTime;

        return status;
    }
}