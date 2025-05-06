package com.example.evcs.exception;

import java.util.List;

public class DuplicateHotdealException extends RuntimeException {
    private final List<String> carNos;
    
    public DuplicateHotdealException(List<String> carNos) {
        super("이미 다른 핫딜이 설정된 차번이 있습니다");
        this.carNos = carNos;
    }
    
    public List<String> getCarNos() { 
    	return carNos; 
	}
}