package org.example.test1234.modules.meetings;

import lombok.Getter;
import org.example.test1234.appError.ErrorMessage;

@Getter
public enum MeetingError implements ErrorMessage {

    MEETING_ALREADY_EXIST(1000, "Meeting already exist"),
    MEETING_CONFLICT(1001, "That group having a meeting on that time - choose different time"),
    CANNOT_SCHEDULE_STARTING_MEETING_BEFORE_NOW(1002, "Wrong schedule - Starting a meeting before now it's impossible"),
    CANNOT_SCHEDULE_ENDING_MEETING_BEFORE_NOW(1003, "Wrong schedule - Ending a meeting before now it's impossible"),
    MEETING_NOT_FOUND(1004, "Meeting not found"),
    STARTING_MEETING_MUST_BE_BEFORE_ITS_END(1005, "Meeting should start before its end"),
    TOO_LONG_MEETING(1006, "That's too much - The maximum time for a meeting is about 10 hours"),
    TOO_SHORT_MEETING(1007, "That's too short - The minimum time for a meeting is about 1 minute");

    private final int code;
    private final String message;

    MeetingError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return this.code + " " + this.message;
    }
}