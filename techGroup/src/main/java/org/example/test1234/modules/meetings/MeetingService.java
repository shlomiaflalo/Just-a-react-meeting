package org.example.test1234.modules.meetings;

import org.example.test1234.appError.AppException;

import java.util.List;

public interface MeetingService {
    Meeting addMeeting(Meeting meeting, int techGroupId) throws AppException;

    List<Meeting> getMeetingList(int techGroupId);

    int meetingDuration(int meetingId) throws AppException;
}
