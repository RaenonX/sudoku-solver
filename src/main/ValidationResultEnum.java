package main;

public enum ValidationResultEnum {
    SuccessFilled {
        @Override
        public String toString() {
            return "Success with filled";
        }
    },
    SuccessWithEmpty {
        @Override
        public String toString() {
            return "Success with empty entries";
        }
    },
    Failed {
        @Override
        public String toString() {
            return "Failed";
        }
    }
}
