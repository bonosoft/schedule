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

import java.util.ArrayList;

/**
 * by bn on 02/12/2018.
 */
public class ScheduleTrigger {
    ArrayList<ScheduleType> scheduleTypes = new ArrayList<>();

    public boolean minuteCheck(ScheduleTime scheduleTime) {
        boolean trigger = scheduleTypes.contains(ScheduleType.everyMinute);
        if (!trigger && scheduleTypes.contains(ScheduleType.every5minutes)) {
            trigger = (scheduleTime.minute % 5) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every15minutes)) {
            trigger = (scheduleTime.minute % 15) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every20minutes)) {
            trigger = (scheduleTime.minute % 20) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every30minutes)) {
            trigger = (scheduleTime.minute % 30) == 0;
        }
        return limit(trigger, scheduleTime);
    }

    public boolean hourCheck(ScheduleTime scheduleTime) {
        boolean trigger = scheduleTypes.contains(ScheduleType.everyHour);
        if (!trigger && scheduleTypes.contains(ScheduleType.every2hours)) {
            trigger = (scheduleTime.hour % 2) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every3hours)) {
            trigger = (scheduleTime.hour % 3) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every4hours)) {
            trigger = (scheduleTime.hour % 4) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every6hours)) {
            trigger = (scheduleTime.hour % 6) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every8hours)) {
            trigger = (scheduleTime.hour % 8) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every12hours)) {
            trigger = (scheduleTime.hour % 12) == 0;
        }
        return limit(trigger, scheduleTime);
    }

    public boolean dayCheck(ScheduleTime scheduleTime) {
        boolean trigger = scheduleTypes.contains(ScheduleType.everyDay);
        if (!trigger && scheduleTypes.contains(ScheduleType.every2days)) {
            trigger = ((scheduleTime.date-1) % 2) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every3days)) {
            trigger = ((scheduleTime.date-1) % 3) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every4days)) {
            trigger = ((scheduleTime.date-1) % 4) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every5days)) {
            trigger = ((scheduleTime.date-1) % 5) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every6days)) {
            trigger = ((scheduleTime.date-1) % 6) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every7days)) {
            trigger = ((scheduleTime.date-1) % 7) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every8days)) {
            trigger = ((scheduleTime.date-1) % 8) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every14days)) {
            trigger = ((scheduleTime.date-1) % 14) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every21days)) {
            trigger = ((scheduleTime.date-1) % 21) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every28days)) {
            trigger = ((scheduleTime.date-1) % 28) == 0;
        }
        return limit(trigger, scheduleTime);
    }

    public boolean monthCheck(ScheduleTime scheduleTime) {
        boolean trigger = scheduleTypes.contains(ScheduleType.everyMonth);
        if (!trigger && scheduleTypes.contains(ScheduleType.every2months)) {
            trigger = ((scheduleTime.month-1) % 2) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every3months)) {
            trigger = ((scheduleTime.month-1) % 3) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every4months)) {
            trigger = ((scheduleTime.month-1) % 4) == 0;
        }
        if (!trigger && scheduleTypes.contains(ScheduleType.every6months)) {
            trigger = ((scheduleTime.month-1) % 6) == 0;
        }
        return trigger;
    }

    public boolean yearCheck(ScheduleTime scheduleTime) {
        return scheduleTypes.contains(ScheduleType.everyYear);
    }

    private boolean limit(boolean trigger, ScheduleTime scheduleTime) {

        if (trigger && ScheduleType.contains(scheduleTypes, ScheduleType.weekdays)) {
            trigger = ScheduleType.contains(scheduleTime.dayOfWeek, scheduleTypes, ScheduleType.weekdays);
        }

        if (trigger && ScheduleType.contains(scheduleTypes, ScheduleType.hours)) {
            trigger = ScheduleType.contains(scheduleTime.hour, scheduleTypes, ScheduleType.hours);
        }

        return trigger;
    }
}
