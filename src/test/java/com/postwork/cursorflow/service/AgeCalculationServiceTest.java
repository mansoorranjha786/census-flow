package com.postwork.cursorflow.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AgeCalculationServiceTest {

    private static AgeCalculationService ageCalculationService;

    @BeforeAll
    static void setUp() {
        ageCalculationService = new AgeCalculationService();
    }

    @Test
    @DisplayName("Should calculate the correct age for a 30-year old")
    void calculateAgeFor30YearOld() {
        LocalDate dob = LocalDate.of(1996, 1, 1);
        LocalDate now = LocalDate.now();
        int age = ageCalculationService.calculateInsuranceAge(dob, now);
        assertEquals(30, age);
    }

    @Test
    @DisplayName("Should calculate the correctly on exact birthday")
    void calculateAgeOnBirthday() {
        LocalDate dob = LocalDate.of(1996, 3, 4);
        LocalDate asOf = LocalDate.of(2026, 3, 4);
        int age = ageCalculationService.calculateInsuranceAge(dob, asOf);
        assertEquals(30, age);
    }

    @Test
    @DisplayName("Should throw exception for future dob")
    void shouldThrowFutureDobException() {
        LocalDate futureDob = LocalDate.now().plusDays(1);
        assertThrows(IllegalArgumentException.class, () -> ageCalculationService.calculateInsuranceAge(futureDob, LocalDate.now()));
    }

    @Test
    @DisplayName("Should throw exception for null dob")
    void shouldThrowNullDobException() {
        assertThrows(IllegalArgumentException.class, () -> ageCalculationService.calculateInsuranceAge(null, LocalDate.now()));
    }

    @Test
    @DisplayName("Should mark under 18 as ineligible")
    void shouldMarkUnder18AsIneligible() {
        assertFalse(ageCalculationService.isEligibleByAge(17));
    }

    @Test
    @DisplayName("Should mark over 65 as ineligible")
    void shouldMarkOver65AsIneligible() {
        assertFalse(ageCalculationService.isEligibleByAge(75));
    }

    @Test
    @DisplayName("Should mark 33 as eligible")
    void shouldMark33AsEligible() {
        assertTrue(ageCalculationService.isEligibleByAge(33));
    }
}
