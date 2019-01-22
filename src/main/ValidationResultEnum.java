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
    FailedConflicted {
        @Override
        public String toString() {
            return "Failed because conflicted";
        }
    },
    FailedOutOfBound {
        @Override
        public String toString() {
            return "Failed because out of bound";
        }
    },
    FailedEntryExisted {
        @Override
        public String toString() {
            return "Failed because entry already existed";
        }
    }
}
