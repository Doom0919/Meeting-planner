package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class PersonTest {
    @Test
    public void testPersonCreation() {
        String name = "John Smith";
        Person person = new Person(name);
        assertEquals("Person name should match constructor argument", name, person.getName());
        
        // Test default constructor
        Person defaultPerson = new Person();
        assertEquals("Default person should have empty name", "", defaultPerson.getName());
    }
    
    @Test
    public void testMeetingManagement() {
        Person person = new Person("Alice Johnson");
        Meeting meeting = new Meeting(5, 10, "Project Review");
        
        try {
            // Add a meeting
            person.addMeeting(meeting);
            assertTrue("Person should be busy during meeting time", 
                      person.isBusy(5, 10, 0, 23));
            
            // Get and verify the meeting
            Meeting retrieved = person.getMeeting(5, 10, 0);
            assertEquals("Meeting description should match", 
                        "Project Review", retrieved.getDescription());
            
            // Remove the meeting
            person.removeMeeting(5, 10, 0);
            assertFalse("Person should not be busy after meeting removal", 
                       person.isBusy(5, 10, 0, 23));
        } catch (TimeConflictException e) {
            fail("Should not throw TimeConflictException: " + e.getMessage());
        }
    }
}
