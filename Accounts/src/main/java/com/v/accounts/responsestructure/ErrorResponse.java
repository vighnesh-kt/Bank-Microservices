package com.v.accounts.responsestructure;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private final String apiPath;

	private final HttpStatus errorCode;

	private final String errorMessage;

	private final LocalDateTime errorTime;

	public static class ErrorDtoBuilder {

		String apiPath;

		HttpStatus errorCode;

		String errorMessage;

		LocalDateTime errorTime;

		public ErrorDtoBuilder setApiPath(String apiPath) {
			this.apiPath = apiPath;
			return this;
		}

		public ErrorDtoBuilder setErrorCode(HttpStatus errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public ErrorDtoBuilder setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
			return this;
		}

		public ErrorDtoBuilder setErroTime(LocalDateTime errorTime) {
			this.errorTime = errorTime;
			return this;
		}

		public ErrorResponse build() {
			return new ErrorResponse(this);
		}

	}

	private ErrorResponse(ErrorDtoBuilder builder) {
		this.apiPath = builder.apiPath;
		this.errorCode = builder.errorCode;
		this.errorMessage = builder.errorMessage;
		this.errorTime = builder.errorTime;
	}
}
