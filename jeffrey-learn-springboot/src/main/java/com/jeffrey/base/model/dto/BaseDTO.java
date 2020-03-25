package com.jeffrey.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author lijianfei
 *         <p>
 *         </p>
 *         2020年3月20日
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
	@NonNull
	protected String code;
	protected String msg;
}
