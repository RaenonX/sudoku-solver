package main;

import java.util.stream.Stream;

public class ValidationResult {
    ValidationResultEnum result;
    ValidationTargetEnum target;
    int position;

    ValidationResult(ValidationResultEnum result, ValidationTargetEnum target, int position) {
        this.result = result;
        this.target = target;
        this.position = position;
    }

    ValidationResult(ValidationResultEnum result) {
        this.result = result;
        this.target = ValidationTargetEnum.Exception;
        this.position = -1;
    }

    boolean isSuccess() {
        return Stream.of(ValidationResultEnum.SuccessWithEmpty, ValidationResultEnum.SuccessFilled).anyMatch(x -> x == result);
    }

    public ValidationResultEnum getValidationResult() {
        return this.result;
    }

    public String toString() {
        if (this.target != ValidationTargetEnum.Exception) {
            return String.format("Validation %s. Target is %s %d.", this.result, this.target, this.position);
        } else {
            return String.format("Exception occurred. %s", this.result);
        }

    }
}
