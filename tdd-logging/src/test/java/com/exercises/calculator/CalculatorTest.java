package com.exercises.calculator;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Exercises:
 * - Exercise 1: Setting Up JUnit (basic test)
 * - Exercise 3: Assertions in JUnit
 * - Exercise 4: AAA Pattern, Test Fixtures, Setup and Teardown Methods
 */
class CalculatorTest {

    private Calculator calculator;

    // Exercise 4: Setup method (runs before each test)
    @BeforeEach
    void setUp() {
        System.out.println(">> Setting up test fixture...");
        calculator = new Calculator();
    }

    // Exercise 4: Teardown method (runs after each test)
    @AfterEach
    void tearDown() {
        System.out.println(">> Tearing down test fixture...");
        calculator = null;
    }

    // Exercise 1: Basic JUnit test
    @Test
    void testCalculatorInitialization() {
        assertNotNull(calculator, "Calculator should be initialized");
        System.out.println("✅ testCalculatorInitialization passed");
    }

    // Exercise 3: Assertions - testing add()
    @Test
    void testAdd() {
        // AAA Pattern: Arrange
        int a = 5;
        int b = 3;
        int expected = 8;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(expected, result, "5 + 3 should equal 8");
        assertTrue(result > 0);
        assertFalse(result < 0);
        System.out.println("✅ testAdd passed");
    }

    // Exercise 3: Assertions - testing subtract()
    @Test
    void testSubtract() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
        assertNotEquals(5, result);
        System.out.println("✅ testSubtract passed");
    }

    // Exercise 3: Assertions - testing multiply()
    @Test
    void testMultiply() {
        int result = calculator.multiply(6, 7);
        assertEquals(42, result);
        assertAll(
            () -> assertEquals(0, calculator.multiply(0, 5)),
            () -> assertEquals(-10, calculator.multiply(-2, 5))
        );
        System.out.println("✅ testMultiply passed");
    }

    // Exercise 3: Testing exceptions
    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        });
        System.out.println("✅ testDivideByZero passed");
    }

    // Exercise 4: Parameterized test (part of assertions)
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "5, 5, 10",
        "-3, 7, 4"
    })
    void testAddWithMultipleInputs(int a, int b, int expected) {
        // AAA Pattern: Arrange, Act, Assert all in one
        assertEquals(expected, calculator.add(a, b));
        System.out.println("✅ testAddWithMultipleInputs: " + a + " + " + b + " = " + expected);
    }
}