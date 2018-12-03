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
public enum ScheduleType {

    everyMinute,
    everyHour,
    everyDay,
    everyMonth,
    everyYear,

    every5minutes,
    every15minutes,
    every20minutes,
    every30minutes,

    every2hours,
    every3hours,
    every4hours,
    every6hours,
    every8hours,
    every12hours,

    every2days,
    every3days,
    every4days,
    every5days,
    every6days,
    every7days,
    every8days,
    every14days,
    every21days,
    every28days,

    every2months,
    every3months,
    every4months,
    every6months,


    at0hour,
    at1hour,
    at2hour,
    at3hour,
    at4hour,
    at5hour,
    at6hour,
    at7hour,
    at8hour,
    at9hour,
    at10hour,
    at11hour,
    at12hour,
    at13hour,
    at14hour,
    at15hour,
    at16hour,
    at17hour,
    at18hour,
    at19hour,
    at20hour,
    at21hour,
    at22hour,
    at23hour,

    atMonday (2),
    atTuesday (3),
    atWedensday (4),
    atThrusday (5),
    atFriday (6),
    atSaturday (7),
    atSunday (1);

    private int id;

    ScheduleType() {
        this.id = 0;
    }

    ScheduleType(int id) {
        this.id = id;
    }

    public static ScheduleType[] weekdays = new ScheduleType[] {
            atMonday, atTuesday, atWedensday, atThrusday, atFriday, atSaturday, atSunday
    };

    public static ScheduleType[] hours = new ScheduleType[] {
            at0hour, at1hour, at2hour, at3hour, at4hour, at5hour,
            at6hour, at7hour, at8hour, at9hour, at10hour, at11hour,
            at12hour, at13hour, at14hour, at15hour, at16hour, at17hour,
            at18hour, at19hour, at20hour, at21hour, at22hour, at23hour
    };

    public static boolean contains(ArrayList<ScheduleType> s, ScheduleType[] types) {
        for(ScheduleType scheduleType : types) {
            if (s.contains(scheduleType)) return true;
        }
        return false;
    }

    public static boolean contains(int id, ArrayList<ScheduleType> s, ScheduleType[] types) {
        for(ScheduleType scheduleType : types) {
            if (s.contains(scheduleType)) {
                if (scheduleType.id == id) {
                    return true;
                }
            }
        }
        return false;
    }

}
