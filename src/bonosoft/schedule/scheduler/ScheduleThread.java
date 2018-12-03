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

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *  by bn on 02/12/2018.
 */
public class ScheduleThread extends Thread {
    private boolean doRun = true;
    private Calendar calendar;

    private int year;
    private int month;

    private int dayOfWeek;
    private int date;

    private int hour;
    private int minute;

    private void init() {
        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.ENGLISH);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DATE);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    private void tick() {
        int check = calendar.get(Calendar.MINUTE);
        if (check!=minute) {
            Schedule.getInstance().minuteTick(new ScheduleTime(year, month, dayOfWeek, date, hour, minute));
            minute = check;
        }

        check = calendar.get(Calendar.HOUR_OF_DAY);
        if (check!=hour) {
            Schedule.getInstance().hourTick(new ScheduleTime(year, month, dayOfWeek, date, hour, minute));
            hour = check;
        }

        check = calendar.get(Calendar.DATE);
        if (check!=date) {
            Schedule.getInstance().dayTick(new ScheduleTime(year, month, dayOfWeek, date, hour, minute));
            date = check;
        }

        check = calendar.get(Calendar.MONTH);
        if (check!=month) {
            Schedule.getInstance().monthTick(new ScheduleTime(year, month, dayOfWeek, date, hour, minute));
            month = check;
        }

        check = calendar.get(Calendar.YEAR);
        if (check!=year) {
            Schedule.getInstance().yearTick(new ScheduleTime(year, month, dayOfWeek, date, hour, minute));
            year = check;
        }
    }


    @Override
    public void run() {
        init();
        while (doRun) {
            try {
                Thread.sleep(1000);  // sleep for 1 second...
                if (doRun) {
                    tick();
                }
            } catch (InterruptedException e) {
                System.err.println("Scheduler was interrupted");
            }
        }
    }

    public void close() {
        doRun = false;
    }
}
