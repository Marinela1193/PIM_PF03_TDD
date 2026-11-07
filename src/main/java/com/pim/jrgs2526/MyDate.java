package com.pim.jrgs2526;

public class MyDate {

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";

    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth(int monthNumber) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }

    private int day;
    private Months month;
    private int year;

    public MyDate() {
        this(1, Months.JANUARY, 2000);
    }

    public MyDate(int days, Months month, int year) {
        if(month == null || days < 1 || days > maxDaysMonth(month, year)) {
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        }
        this.day = days;
        this.month = month;
        this.year = year;
    }

    public static int maxDaysMonth(Months month, int year){
        if (month == null) {
            return -1;
        }
        switch (month) {
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            case FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 31;
        }
    }

    public int getDay() {
        return day;
    }
    public Months getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        if(day < 1 || day > maxDaysMonth(month, year)) {
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        }
        this.day = day;
    }

    public void setMonth(Months month) {
        if(month == null | this.day > maxDaysMonth(month, year)) {
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }
        this.month = month;
    }
    public void setYear(int year) {
        if(this.day > maxDaysMonth(this.month, year)) {
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        }
        this.year = year;
    }

    public static boolean isLeapYear(int year) {
        if (year < 0) {
            return false;
        }
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
