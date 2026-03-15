package com.postwork.cursorflow.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AgeCalculationService {

    /**
     * @param dob      -the date of birth
     * @param asOfDate -the reference date
     * @return -the calculated insurance age in year
     */
    public int calculateInsuranceAge(LocalDate dob, LocalDate asOfDate) {
        if (dob == null || asOfDate == null) {
            throw new IllegalArgumentException("Date of birth and reference date cannot be null");
        }
        if (dob.isAfter(asOfDate)) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        Period period = Period.between(dob, asOfDate);

        int age = period.getYears();

        LocalDate lastBirthday = dob.plusYears(age);
        LocalDate nextBirthday = dob.plusYears(age + 1);

        long daysToNext = asOfDate.until(nextBirthday).getDays() +
                asOfDate.until(nextBirthday).getMonths() * 30L +
                asOfDate.until(nextBirthday).getYears() * 365L;

        long daysSinceLast = lastBirthday.until(asOfDate).getDays() +
                lastBirthday.until(asOfDate).getMonths() * 30L;

        if (daysSinceLast > daysToNext) {
            return age + 1;
        }
        return age;
    }

    public int calculateChronologicalAge(LocalDate dob, LocalDate asOfDate) {
        if (dob == null || asOfDate == null) {
            throw new IllegalArgumentException("Date of birth and reference date cannot be null");
        }
        if (dob.isAfter(asOfDate)) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        return Period.between(dob, asOfDate).getYears();
    }

    public boolean isEligibleByAge(int age) {
        return age >= 18 && age <= 65;
    }

    public String getIneligibilityReason(int age) {
        if (age < 18) {
            return "Employee is under 18 year of age: " + age;
        }
        if (age > 65) {
            return "Employee is over 65 year of age: " + age;
        }
        return null;
    }
}
