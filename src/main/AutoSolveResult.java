package main;

import java.time.Duration;
import java.time.Instant;

public class AutoSolveResult {
    private int operations;
    private Instant start;
    private Instant end;
    private boolean success;

    AutoSolveResult() {
        this.operations = 0;
        this.success = false;
    }

    void recordStart() {
        this.start = Instant.now();
    }

    void recordEnd(boolean success) {
        this.end = Instant.now();
        this.success = success;
    }

    void recordOperations() {
        this.operations++;
    }

    public String toString() {
        if (this.start == null) {
            return "No records";
        } else if (this.end == null) {
            return String.format("Recording... %d operations %d ms elapsed", this.operations, Duration.between(this.start, Instant.now()).toMillis());
        } else {
            return String.format("%d operations in %d ms", this.operations, Duration.between(this.start, this.end).toMillis());
        }
    }
}
