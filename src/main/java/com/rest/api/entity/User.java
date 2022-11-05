package com.rest.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("my_username")
    private String username;

    @JsonProperty("my_email")
    private String email;

    @JsonProperty("my_password")
    private String password;

    @JsonProperty("my_phone")
    private String phoneNumber;

    @JsonProperty("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date endTime;

    @JsonProperty("created_date")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "Europe/Zagreb")
    private Date createdDate;

    @JsonProperty("last_modified_date")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date lastModifiedDate;
}
