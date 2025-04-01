package org.example.test1234.clr;

import lombok.RequiredArgsConstructor;
import org.example.test1234.modules.meetings.Meeting;
import org.example.test1234.modules.meetings.MeetingService;
import org.example.test1234.modules.tech_groups.TechGroup;
import org.example.test1234.modules.tech_groups.TechGroupService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DemoClr implements CommandLineRunner {
    private final TechGroupService techGroupService;
    private final MeetingService meetingService;


    @Override
    public void run(String... args) throws Exception {
        TechGroup techGroup1 = TechGroup.builder().groupName("Flying with java").build();
        TechGroup techGroup2 = TechGroup.builder().groupName("The coders").build();
        TechGroup techGroup3 = TechGroup.builder().groupName("Only JS").build();

        Meeting meeting1 = Meeting.builder().startTime(
                        LocalDateTime.now().plusHours(6)).endTime(LocalDateTime.now().plusHours(10))
                .description("Just a meeting").roomName("Thinking room").build();

        Meeting meeting2 = Meeting.builder().startTime(
                        LocalDateTime.now().plusHours(5)).endTime(LocalDateTime.now().plusHours(9))
                .description("Just a meeting with moshe").roomName("Fun room").build();

        Meeting meeting3 = Meeting.builder().startTime(
                        LocalDateTime.now().plusHours(6)).endTime(LocalDateTime.now().plusHours(13))
                .description("Just a meeting with idan").roomName("Sports room").build();

        this.techGroupService.addTechGroup(techGroup1);
        this.techGroupService.addTechGroup(techGroup2);
        this.techGroupService.addTechGroup(techGroup3);

        this.meetingService.addMeeting(meeting1, 1);
        this.meetingService.addMeeting(meeting2, 2);
        this.meetingService.addMeeting(meeting3, 3);

        this.techGroupService.getTechGroupList().forEach(System.out::println);
        this.meetingService.getMeetingList(1).forEach(System.out::println);


    }
}
