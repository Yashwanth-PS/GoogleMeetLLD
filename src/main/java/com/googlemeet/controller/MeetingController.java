package com.googlemeet.controller;

import com.googlemeet.dto.MeetingJoinRequestDTO;
import com.googlemeet.dto.MeetingResponseDTO;
import com.googlemeet.model.Meeting;
import com.googlemeet.service.MeetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.googlemeet.mapper.MeetingMapper.convertMeetingToMeetingResponseDTO;

@RestController
@RequestMapping("/meeting")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping("/create")
    public ResponseEntity<MeetingResponseDTO> createMeeting() {
        try {
            Meeting scheduledMeeting = meetingService.createMeeting();
            MeetingResponseDTO meetingResponseDTO = convertMeetingToMeetingResponseDTO(scheduledMeeting);
            return ResponseEntity.status(HttpStatus.CREATED).body(meetingResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/join")
    public ResponseEntity<Boolean> joinMeeting(@RequestBody MeetingJoinRequestDTO joinRequest) {
        try {
            Boolean meetingStatus = meetingService.joinMeeting(joinRequest.getMeetingId(), joinRequest.getMeetingPassword());
            if (meetingStatus) {
                return ResponseEntity.ok(meetingStatus);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(meetingStatus);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/exit")
    public ResponseEntity<Boolean> exitMeeting(@RequestBody int meetingId) {
        try {
            Boolean exitStatus = meetingService.exitMeeting(meetingId);
            if (exitStatus) {
                return ResponseEntity.ok(exitStatus);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exitStatus);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
