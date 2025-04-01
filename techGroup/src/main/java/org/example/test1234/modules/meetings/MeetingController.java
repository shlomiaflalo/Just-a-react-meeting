package org.example.test1234.modules.meetings;

import lombok.RequiredArgsConstructor;
import org.example.test1234.appError.AppException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/meeting")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;

    @GetMapping("/{techGroupId}")
    public List<Meeting> getMeetingList(@PathVariable int techGroupId) {
        return meetingService.getMeetingList(techGroupId);
    }

    @PostMapping("/{techGroupId}")
    public Meeting addMeeting(@RequestBody Meeting meeting, @PathVariable int techGroupId) throws AppException {
        return meetingService.addMeeting(meeting, techGroupId);
    }

    @GetMapping("duration/{meetingId}")
    public int meetingDuration(@PathVariable int meetingId) throws AppException {
       return meetingService.meetingDuration(meetingId);
    }

}
