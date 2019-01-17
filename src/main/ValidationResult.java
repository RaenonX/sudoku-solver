package main;

public class ValidationResult {
    ValidationResultEnum result;
    ValidationTargetEnum target;
    int position;

    ValidationResult(ValidationResultEnum result, ValidationTargetEnum target, int position) {
        this.result = result;
        this.target = target;
        this.position = position;
    }

    boolean getSuccess() {
        return this.result != ValidationResultEnum.Failed;
    }

    public ValidationResultEnum getValidationResult() {
        return this.result;
    }

    public String toString() {
        return String.format("Validation %s. Target is %s %d.", this.result, this.target, this.position);
    }
}
