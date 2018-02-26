package cn.wolfcode.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private Long id;

    private String username;

    private String phoneNumber;

    private String password;

}