package com.pd.binding;

import lombok.Data;

@Data
public class UnlockAccountForm {
	private String confirmPwd;
	private String tempPwd;
	private String newPwd;
	private String email;
}
