package com.board.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
*/

// @Data : @Getter @Setter @ToString
//         @EqualsAndHashCode, @RequiredArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
	private String userid;
	private String passwd;
	private String username;
	private String email;
	private String upoint;
	private String indate;
}
