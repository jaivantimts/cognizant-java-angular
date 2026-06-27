-- =====================================================
-- PL/SQL Exercise 1: Control Structures
-- Scenario: Student Grade Management System
-- =====================================================

-- Enable output to console
SET SERVEROUTPUT ON;

-- =====================================================
-- Block 1: IF-THEN-ELSE for Grade Classification
-- =====================================================
DECLARE
    v_score NUMBER := 85;   -- Student's score (change to test)
    v_grade CHAR(1);
BEGIN
    -- IF-THEN-ELSIF structure to determine grade
    IF v_score >= 90 THEN
        v_grade := 'A';
    ELSIF v_score >= 80 THEN
        v_grade := 'B';
    ELSIF v_score >= 70 THEN
        v_grade := 'C';
    ELSIF v_score >= 60 THEN
        v_grade := 'D';
    ELSE
        v_grade := 'F';
    END IF;
    
    -- Output the result
    DBMS_OUTPUT.PUT_LINE('Score: ' || v_score || ' → Grade: ' || v_grade);
END;
/


-- =====================================================
-- Block 2: CASE Statement for Category
-- =====================================================
DECLARE
    v_department VARCHAR2(20) := 'IT';
    v_category VARCHAR2(20);
BEGIN
    -- CASE statement for department category
    v_category := CASE v_department
        WHEN 'IT' THEN 'Technology'
        WHEN 'HR' THEN 'Human Resources'
        WHEN 'Finance' THEN 'Financial Services'
        WHEN 'Marketing' THEN 'Branding'
        ELSE 'General'
    END;
    
    DBMS_OUTPUT.PUT_LINE('Department: ' || v_department || ' → Category: ' || v_category);
END;
/


-- =====================================================
-- Block 3: Loops (FOR loop with CURSOR)
-- =====================================================
-- NOTE: This requires a 'students' table to exist.
-- If the table doesn't exist, run the CREATE TABLE first.
-- =====================================================

-- Create a sample table (run this once)
CREATE TABLE students (
    student_id NUMBER PRIMARY KEY,
    name VARCHAR2(50),
    grade CHAR(1)
);

-- Insert sample data
INSERT INTO students VALUES (1, 'Alice Johnson', 'A');
INSERT INTO students VALUES (2, 'Bob Smith', 'B');
INSERT INTO students VALUES (3, 'Carol White', 'C');
INSERT INTO students VALUES (4, 'David Brown', 'A');
INSERT INTO students VALUES (5, 'Eve Davis', 'F');

-- Display all students using a FOR loop
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Student List ---');
    FOR student_rec IN (SELECT * FROM students ORDER BY student_id) LOOP
        DBMS_OUTPUT.PUT_LINE(
            'ID: ' || student_rec.student_id || 
            ', Name: ' || student_rec.name || 
            ', Grade: ' || student_rec.grade
        );
    END LOOP;
END;
/


-- =====================================================
-- Block 4: WHILE Loop with Exit Condition
-- =====================================================
DECLARE
    v_counter NUMBER := 1;
    v_sum NUMBER := 0;
BEGIN
    -- Calculate sum of numbers 1 to 10 using WHILE loop
    WHILE v_counter <= 10 LOOP
        v_sum := v_sum + v_counter;
        v_counter := v_counter + 1;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Sum of numbers 1 to 10: ' || v_sum);
END;
/


-- =====================================================
-- Cleanup (optional - uncomment if needed)
-- =====================================================
-- DROP TABLE students;