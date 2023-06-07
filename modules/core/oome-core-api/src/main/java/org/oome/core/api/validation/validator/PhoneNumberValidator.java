package org.oome.core.api.validation.validator;

import org.oome.core.api.validation.annotations.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        // 초기화 작업을 수행할 경우 여기에 작성
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 휴대폰 번호 유효성 검사
        if (value == null || value.isEmpty()) {
            // 빈 값은 검사하지 않음
            return true;
        }

        // 숫자와 "-"를 제외한 모든 문자 제거
        String cleanedNumber = value.replaceAll("[^0-9-]", "");

        // 형식 검사
        if (cleanedNumber.matches("01[0-9]-[0-9]{3,4}-[0-9]{4}")) {
            // "010-XXXX-XXXX" 형식에 일치하는 경우 유효함
            return true;
        } else if (cleanedNumber.matches("01[0-9]{2}-[0-9]{3,4}-[0-9]{4}")) {
            // "01X-XXX-XXXX" 형식에 일치하는 경우 유효함
            return true;
        }

        return false;
    }
}