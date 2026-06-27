-- =====================================================
-- PL/SQL Exercise 3: Stored Procedures
-- Scenario: Employee Management System
-- =====================================================

SET SERVEROUTPUT ON;

-- =====================================================
-- Create sample tables (run this once)
-- =====================================================

CREATE TABLE employees (
    emp_id NUMBER PRIMARY KEY,
    emp_name VARCHAR2(50),
    salary NUMBER(10,2),
    department VARCHAR2(30),
    hire_date DATE
);

-- Insert sample data
INSERT INTO employees VALUES (101, 'John Doe', 55000, 'IT', DATE '2020-05-15');
INSERT INTO employees VALUES (102, 'Jane Smith', 62000, 'HR', DATE '2019-08-22');
INSERT INTO employees VALUES (103, 'Mike Johnson', 48000, 'Finance', DATE '2021-02-10');
INSERT INTO employees VALUES (104, 'Sarah Williams', 75000, 'IT', DATE '2018-11-01');
INSERT INTO employees VALUES (105, 'Tom Brown', 52000, 'Marketing', DATE '2020-09-12');


-- =====================================================
-- Procedure 1: Get Employee Details by ID (IN parameter)
-- =====================================================
CREATE OR REPLACE PROCEDURE get_employee_details (
    p_emp_id IN NUMBER
) IS
    v_name employees.emp_name%TYPE;
    v_salary employees.salary%TYPE;
    v_dept employees.department%TYPE;
BEGIN
    -- Fetch employee details
    SELECT emp_name, salary, department
    INTO v_name, v_salary, v_dept
    FROM employees
    WHERE emp_id = p_emp_id;
    
    -- Display results
    DBMS_OUTPUT.PUT_LINE('--- Employee Details ---');
    DBMS_OUTPUT.PUT_LINE('ID: ' || p_emp_id);
    DBMS_OUTPUT.PUT_LINE('Name: ' || v_name);
    DBMS_OUTPUT.PUT_LINE('Salary: $' || v_salary);
    DBMS_OUTPUT.PUT_LINE('Department: ' || v_dept);
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Employee ID ' || p_emp_id || ' not found.');
END get_employee_details;
/


-- =====================================================
-- Procedure 2: Give Salary Raise (IN and OUT parameters)
-- =====================================================
CREATE OR REPLACE PROCEDURE give_salary_raise (
    p_emp_id IN NUMBER,
    p_raise_percent IN NUMBER,
    p_new_salary OUT NUMBER
) IS
BEGIN
    -- Update salary
    UPDATE employees
    SET salary = salary * (1 + p_raise_percent / 100)
    WHERE emp_id = p_emp_id
    RETURNING salary INTO p_new_salary;
    
    -- Commit the transaction
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Salary raised successfully. New salary: $' || p_new_salary);
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END give_salary_raise;
/


-- =====================================================
-- Procedure 3: Get Employees by Department (CURSOR as OUT)
-- =====================================================
CREATE OR REPLACE PROCEDURE get_employees_by_dept (
    p_dept IN VARCHAR2,
    p_cursor OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_cursor FOR
        SELECT emp_id, emp_name, salary
        FROM employees
        WHERE UPPER(department) = UPPER(p_dept)
        ORDER BY emp_name;
END get_employees_by_dept;
/


-- =====================================================
-- Function: Calculate Annual Bonus (IN parameter, RETURN value)
-- =====================================================
CREATE OR REPLACE FUNCTION calculate_bonus (
    p_salary IN NUMBER,
    p_years_of_service IN NUMBER
) RETURN NUMBER IS
    v_bonus NUMBER(10,2);
BEGIN
    -- Bonus calculation: 5% of salary for each year of service
    -- Maximum bonus: 50% of salary
    v_bonus := (p_salary * 0.05) * LEAST(p_years_of_service, 10);
    RETURN v_bonus;
END calculate_bonus;
/


-- =====================================================
-- Test the Procedures and Function
-- =====================================================

-- Test Procedure 1: Get Employee 101 details
BEGIN
    get_employee_details(101);
END;
/

-- Test Procedure 2: Give 10% raise to Employee 102
DECLARE
    v_new_salary NUMBER;
BEGIN
    give_salary_raise(102, 10, v_new_salary);
    DBMS_OUTPUT.PUT_LINE('Raise applied. New salary: $' || v_new_salary);
END;
/

-- Test Function: Calculate bonus for an employee
DECLARE
    v_bonus NUMBER;
BEGIN
    -- Check salary first
    FOR emp IN (SELECT emp_id, emp_name, salary FROM employees WHERE emp_id = 103) LOOP
        v_bonus := calculate_bonus(emp.salary, 5);  -- 5 years of service
        DBMS_OUTPUT.PUT_LINE('Bonus for ' || emp.emp_name || ': $' || v_bonus);
    END LOOP;
END;
/

-- Test Procedure 3: Get all IT employees
DECLARE
    dept_cursor SYS_REFCURSOR;
    v_id employees.emp_id%TYPE;
    v_name employees.emp_name%TYPE;
    v_sal employees.salary%TYPE;
BEGIN
    get_employees_by_dept('IT', dept_cursor);
    DBMS_OUTPUT.PUT_LINE('--- IT Employees ---');
    LOOP
        FETCH dept_cursor INTO v_id, v_name, v_sal;
        EXIT WHEN dept_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('ID: ' || v_id || ', Name: ' || v_name || ', Salary: $' || v_sal);
    END LOOP;
    CLOSE dept_cursor;
END;
/


-- =====================================================
-- Cleanup (uncomment if needed)
-- =====================================================
-- DROP PROCEDURE get_employee_details;
-- DROP PROCEDURE give_salary_raise;
-- DROP PROCEDURE get_employees_by_dept;
-- DROP FUNCTION calculate_bonus;
-- DROP TABLE employees;