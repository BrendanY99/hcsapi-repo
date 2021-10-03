package com.sprint1.hcsapi.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.sprint1.hcsapi.service.MapValidationErrorService;

@Service
public class MapValidationErrorServiceImpl implements MapValidationErrorService {

	@Override
	public ResponseEntity<?> MapValidationError(BindingResult result) {
		if(result.hasErrors()) {
			Map<String,String> errorMap=new HashMap<>();
			for(FieldError fieldError : result.getFieldErrors()) {
				errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}
