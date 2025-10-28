package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class CalendarTest {
    private Calendar calendar;
    
    @Before
    public void setUp() {
        calendar = new Calendar();
    }
    
    @Test
    public void testAddMeeting_holiday() {
        try {
            Meeting midsommar = new Meeting(6, 26, "Midsommar");
            calendar.addMeeting(midsommar);
            Boolean added = calendar.isBusy(6, 26, 0, 23);
            assertTrue("Midsommar should be marked as busy on the calendar", added);
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testInvalidDate() {
        try {
            Meeting meeting = new Meeting(13, 1, "Invalid Month Meeting");
            calendar.addMeeting(meeting);
            fail("Should throw exception for invalid month");
        } catch(TimeConflictException e) {
            assertEquals("Month does not exist.", e.getMessage());
        }
        
        try {
            Meeting meeting = new Meeting(2, 32, "Invalid Day Meeting");
            calendar.addMeeting(meeting);
            fail("Should throw exception for invalid day");
        } catch(TimeConflictException e) {
            assertEquals("Day does not exist.", e.getMessage());
        }
    }
    
    @Test
    public void testInvalidTime() {
        try {
            Meeting meeting = new Meeting(1, 1, "Invalid Start Time Meeting");
            meeting.setStartTime(-1);
            meeting.setEndTime(5);
            calendar.addMeeting(meeting);
            fail("Should throw exception for invalid start time");
        } catch(TimeConflictException e) {
            assertEquals("Illegal hour.", e.getMessage());
        }
        
        try {
            Meeting meeting = new Meeting(1, 1, "Invalid End Time Meeting");
            meeting.setStartTime(10);
            meeting.setEndTime(24);
            calendar.addMeeting(meeting);
            fail("Should throw exception for invalid end time");
        } catch(TimeConflictException e) {
            assertEquals("Illegal hour.", e.getMessage());
        }
    }
    
    @Test
    public void testTimeConflict() {
        try {
         
            Meeting meeting1 = new Meeting(3, 15, "Morning Meeting");
            meeting1.setStartTime(10);
            meeting1.setEndTime(12);
            calendar.addMeeting(meeting1);
            
           
            Meeting meeting2 = new Meeting(3, 15, "Overlapping Meeting");
            meeting2.setStartTime(11);
            meeting2.setEndTime(13);
            calendar.addMeeting(meeting2);
            fail("Should throw exception for time conflict");
        } catch(TimeConflictException e) {
            assertTrue(e.getMessage().contains("Overlap with another item"));
        }
    }
    
    
    @Test
    public void testEdgeCases() {
        try {
          
            Meeting newYear = new Meeting(1, 1, "New Year Meeting");
            newYear.setStartTime(0);
            newYear.setEndTime(1);
            calendar.addMeeting(newYear);
            assertTrue("New Year meeting should be added", calendar.isBusy(1, 1, 0, 1));
            
           
            Meeting lastHour = new Meeting(6, 15, "Late Meeting");
            lastHour.setStartTime(22);
            lastHour.setEndTime(23);
            calendar.addMeeting(lastHour);
            assertTrue("Late meeting should be added", calendar.isBusy(6, 15, 22, 23));
            
           
            Meeting febEnd = new Meeting(2, 28, "February End Meeting");
            febEnd.setStartTime(12);
            febEnd.setEndTime(13);
            calendar.addMeeting(febEnd);
            assertTrue("February meeting should be added", calendar.isBusy(2, 28, 12, 13));
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
}
