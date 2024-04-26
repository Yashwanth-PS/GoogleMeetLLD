package com.googlemeet.mapper;

import com.googlemeet.dto.MeetingResponseDTO;
import com.googlemeet.model.Meeting;

public class MeetingMapper {
    public static MeetingResponseDTO convertMeetingToMeetingResponseDTO(Meeting meeting) {
        MeetingResponseDTO meetingResponseDTO = new MeetingResponseDTO();
        meetingResponseDTO.setMeetingId(meeting.getMeetingId());
        meetingResponseDTO.setMeetingPassword(meetingResponseDTO.getMeetingPassword());
        return meetingResponseDTO;
    }
}
