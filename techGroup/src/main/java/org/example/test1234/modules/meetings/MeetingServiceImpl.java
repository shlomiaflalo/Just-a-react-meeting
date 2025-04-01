package org.example.test1234.modules.meetings;

import lombok.RequiredArgsConstructor;
import org.example.test1234.appError.AppException;
import org.example.test1234.modules.tech_groups.TechGroup;
import org.example.test1234.modules.tech_groups.TechGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final TechGroupService techGroupService;

    public boolean isNonOverlapping(LocalDateTime start1, LocalDateTime end1,
                                    LocalDateTime start2, LocalDateTime end2) {
        return end1.isBefore(start2) || start1.isAfter(end2);
    }


    @Override
    @Transactional
    public Meeting addMeeting(Meeting meeting, int techGroupId) throws AppException {
        //If meeting id exists - throw an exception
        if (this.meetingRepository.existsById(meeting.getId())) {
            throw new AppException(MeetingError.MEETING_ALREADY_EXIST);
        }

        //If meeting with its starting date and time is before now - throw an exception
        if (meeting.getStartTime().isBefore(LocalDateTime.now().minusMinutes(1))) {
            throw new AppException(MeetingError.CANNOT_SCHEDULE_STARTING_MEETING_BEFORE_NOW);
        }
        //If meeting with its ending date and time is before now - throw an exception
        if (meeting.getEndTime().isBefore(LocalDateTime.now().minusMinutes(1))) {
            throw new AppException(MeetingError.CANNOT_SCHEDULE_ENDING_MEETING_BEFORE_NOW);
        }

        /*If meeting with its starting date & time is after its
         ending date & time - throw an exception
         */
        if (meeting.getStartTime().isAfter(meeting.getEndTime())) {
            throw new AppException(MeetingError.STARTING_MEETING_MUST_BE_BEFORE_ITS_END);
        }

        //Meeting is too long - exception
        int duration =  (int)
                ChronoUnit.MINUTES.between(meeting.getStartTime(), meeting.getEndTime());
        if (duration>600) {
            throw new AppException(MeetingError.TOO_LONG_MEETING);
        }else if(duration==0){
            throw new AppException(MeetingError.TOO_SHORT_MEETING);
        }

        //If there is already existing meetings with the chosen date & time - throw an exception
        List<Meeting> existingMeetings = meetingRepository.findAllByTechGroup_Id(techGroupId);

        boolean isBusy = existingMeetings.stream()
                .anyMatch(
                        (m) -> !isNonOverlapping(meeting.getStartTime(), meeting.getEndTime(),
                                m.getStartTime(), m.getEndTime())
                );

        if (!isBusy) {
            TechGroup techGroup = this.techGroupService.getSingleTechGroup(techGroupId);
            meeting.setTechGroup(techGroup);
            return this.meetingRepository.save(meeting);
        }

        throw new AppException(MeetingError.MEETING_CONFLICT);

    }

    @Override
    public List<Meeting> getMeetingList(int techGroupId) {
        return this.meetingRepository.findAllByTechGroup_Id(techGroupId);

    }

    @Override
    public int meetingDuration(int meetingId) throws AppException {
        Meeting meeting = this.meetingRepository.findById(meetingId).orElseThrow(
                () -> new AppException(MeetingError.MEETING_NOT_FOUND)
        );

        return (int) ChronoUnit.MINUTES.between(meeting.getStartTime(), meeting.getEndTime());
    }
}
