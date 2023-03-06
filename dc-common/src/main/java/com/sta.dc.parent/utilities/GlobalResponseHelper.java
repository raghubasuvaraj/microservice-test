package com.sta.dc.parent.utilities;

import com.sta.dc.parent.enums.ErrorCodes;
import com.sta.dc.parent.exception.AppException;

public class GlobalResponseHelper {
	
	/**
	 * Use when the API return some response class.
	 * 
	 * @param response
	 * @param data
	 * @param successMessage
	 * @param errorMessage
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static AppResponse createResponse(AppResponse response, Object data, String successMessage,
			String errorMessage) {

		if (data != null) {
			response.setSuccess(true);
			response.setData(data);
			response.setMessage(successMessage);
		} else {
			throw new AppException(ErrorCodes.INTERNAL_SERVER_ERROR, errorMessage);
		}
		return response;
	}

	/**
	 * Use this format when the API returns only boolean
	 * 
	 * @param response
	 * @param flag
	 * @param successMessage
	 * @param errorMessage
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static AppResponse createResponseForFlags(AppResponse response, boolean flag, String successMessage,
			String errorMessage) {
		if (flag) {
			response.setSuccess(flag);
			response.setData(flag);
			response.setMessage(successMessage);
		} else {
			throw new AppException(ErrorCodes.INTERNAL_SERVER_ERROR, errorMessage);
		}
		return response;
	}
}
