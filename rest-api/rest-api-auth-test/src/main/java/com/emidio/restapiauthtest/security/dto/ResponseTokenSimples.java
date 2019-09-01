package com.emidio.restapiauthtest.security.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Response
 * 
 * @param <T>
 */
public class ResponseTokenSimples {

    private TokenSimples data;
	private List<String> errors;

	public ResponseTokenSimples() {
	}

	public TokenSimples getData() {
		return data;
	}

	public void setData(TokenSimples data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {

		String erro = "";
		for (String e : this.errors) {
			erro += e + " | ";
		}

		return "ResponseTokenSimples [token= " + this.data.getToken() + ", errors= " + erro + "]";
	}
}