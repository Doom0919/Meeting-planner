package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class RoomTest {
    @Test
    public void testRoomCreation() {
        // Test default constructor
        Room defaultRoom = new Room();
        assertEquals("Default room should have empty ID", "", defaultRoom.getID());
        
        // Test constructor with ID
        String roomId = "2B01";
        Room room = new Room(roomId);
        assertEquals("Room ID should match constructor argument", roomId, room.getID());
    }
    
    @Test
    public void testRoomScheduling() {
        Room room = new Room("3A01");
        
        try {
            // Create and add a meeting
            Meeting meeting = new Meeting(3, 15, "Department Meeting");
            meeting.setStartTime(14);  // 2 PM
            meeting.setEndTime(16);    // 4 PM
            room.addMeeting(meeting);
            
            // Verify room is busy during meeting time
            assertTrue("Room should be busy during meeting", 
                      room.isBusy(3, 15, 14, 16));
            
            // Verify room is free outside meeting time
            assertFalse("Room should be free before meeting", 
                       room.isBusy(3, 15, 12, 13));
            assertFalse("Room should be free after meeting", 
                       room.isBusy(3, 15, 17, 18));
            
            // Try to schedule overlapping meeting
            Meeting conflictMeeting = new Meeting(3, 15, "Overlapping Meeting");
            conflictMeeting.setStartTime(15);  // 3 PM
            conflictMeeting.setEndTime(17);    // 5 PM
            
            try {
                room.addMeeting(conflictMeeting);
                fail("Should throw TimeConflictException for overlapping meeting");
            } catch (TimeConflictException e) {
                assertTrue("Error message should mention room conflict", 
                          e.getMessage().contains("Conflict for room 3A01"));
            }
            
            // Remove the meeting and verify room is free
            room.removeMeeting(3, 15, 0);
            assertFalse("Room should be free after meeting removal", 
                       room.isBusy(3, 15, 14, 16));
            
        } catch (TimeConflictException e) {
            fail("Should not throw TimeConflictException: " + e.getMessage());
        }
    }
}
