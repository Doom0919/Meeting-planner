package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class OrganizationTest {
    @Test
    public void testOrganizationInitialization() {
        Organization org = new Organization();
        ArrayList<Person> employees = org.getEmployees();
        ArrayList<Room> rooms = org.getRooms();
        
        // Verify initial employees
        assertFalse("Organization should have employees", employees.isEmpty());
        assertTrue("Organization should have Greg Gay as employee",
                  employees.stream().anyMatch(e -> e.getName().equals("Greg Gay")));
        
        // Verify initial rooms
        assertFalse("Organization should have rooms", rooms.isEmpty());
        assertTrue("Organization should have room 2A01",
                  rooms.stream().anyMatch(r -> r.getID().equals("2A01")));
    }
    
    @Test
    public void testEmployeeAndRoomRetrieval() {
        Organization org = new Organization();
        
        try {
            // Test getting existing employee
            Person employee = org.getEmployee("Greg Gay");
            assertNotNull("Should find existing employee", employee);
            assertEquals("Employee name should match", "Greg Gay", employee.getName());
            
            // Test getting existing room
            Room room = org.getRoom("2A01");
            assertNotNull("Should find existing room", room);
            assertEquals("Room ID should match", "2A01", room.getID());
            
            // Test getting non-existent employee
            try {
                org.getEmployee("Non Existent");
                fail("Should throw exception for non-existent employee");
            } catch (Exception e) {
                assertEquals("Should have correct error message", 
                           "Requested employee does not exist", e.getMessage());
            }
            
            // Test getting non-existent room
            try {
                org.getRoom("9Z99");
                fail("Should throw exception for non-existent room");
            } catch (Exception e) {
                assertEquals("Should have correct error message", 
                           "Requested room does not exist", e.getMessage());
            }
        } catch (Exception e) {
            fail("Should not throw exception for existing employee/room: " + e.getMessage());
        }
    }
}
