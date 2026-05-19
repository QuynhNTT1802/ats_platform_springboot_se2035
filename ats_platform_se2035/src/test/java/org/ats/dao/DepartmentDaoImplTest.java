package org.ats.dao;

import org.ats.entities.Department;
import org.ats.entities.DepartmentTitleCompare;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentDaoImplTest {
    private static DepartmentDao departmentDao;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        departmentDao = new DepartmentDaoImpl();
    }

    @Test
    void createDepartment1() {
        // Input
        Department dept = new Department();
        dept.setDepartmentName("IT");
        dept.setDescription("Admin IT");
        // Actual Result

        Department actualResult = departmentDao.createDepartment(dept);

        // Expected Result
        String expectedResult = "IT";

        // Compare
        Assertions.assertEquals(expectedResult, actualResult.getDepartmentName());
    }

    @Test
    void createDepartment2() {
        // Input
        Department dept = new Department();
        dept.setDepartmentName("HR");
        dept.setDescription("Human Resources");
        // Actual Result

        Department actualResult = departmentDao.createDepartment(dept);

        // Expected Result
        String expectedResult = "IT";

        // Compare
        Assertions.assertEquals(expectedResult, actualResult.getDepartmentName());
    }

    @Test
    void createDepartment3() {
        // Input
        Department dept = new Department();
        // Actual Result

        Department actualResult = departmentDao.createDepartment(dept);

        // Compare
        Assertions.assertThrows(Exception.class, ()->{
            throw  new Exception("Test Exception");
        });
    }

    @Test
    void findAll() {
        List<Department> departments = departmentDao.findAll();
        Collections.sort(departments, (o1, o2) -> {
            return  o1.getDepartmentName().compareTo(o2.getDepartmentName());
        });
    }
}