package com.googlemeet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingJoinRequestDTO {
    private int meetingId;
    private String meetingPassword;
}