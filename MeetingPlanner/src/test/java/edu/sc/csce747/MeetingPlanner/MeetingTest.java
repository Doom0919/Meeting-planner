package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class MeetingTest {
    @Test
    public void testConstructorWithDescription() {
        Meeting meeting = new Meeting(6, 15, "Team Meeting");
        assertEquals("Month should be 6", 6, meeting.getMonth());
        assertEquals("Day should be 15", 15, meeting.getDay());
        assertEquals("Start time should be 0", 0, meeting.getStartTime());
        assertEquals("End time should be 23", 23, meeting.getEndTime());
        assertEquals("Description should match", "Team Meeting", meeting.getDescription());
    }
    
    @Test
    public void testAttendeeManagement() {
        // Create meeting with all parameters to ensure attendees list is initialized
        ArrayList<Person> initialAttendees = new ArrayList<>();
        Room room = new Room("TestRoom");
        Meeting meeting = new Meeting(7, 20, 10, 11, initialAttendees, room, "Test Meeting");
        
        Person person1 = new Person("John Doe");
        Person person2 = new Person("Jane Smith");
        
        // Add first person
        meeting.addAttendee(person1);
        ArrayList<Person> attendees = meeting.getAttendees();
        assertEquals("Should have one attendee", 1, attendees.size());
        assertEquals("First attendee should be John Doe", "John Doe", attendees.get(0).getName());
        
        // Add second person
        meeting.addAttendee(person2);
        assertEquals("Should have two attendees", 2, attendees.size());
        
        // Remove first person
        meeting.removeAttendee(person1);
        assertEquals("Should have one attendee after removal", 1, attendees.size());
        assertEquals("Remaining attendee should be Jane Smith", "Jane Smith", attendees.get(0).getName());
    }
}
