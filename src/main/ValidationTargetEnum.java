package main;

public enum ValidationTargetEnum {
    Row {
        @Override
        public String toString() {
            return "row";
        }
    },
    Column {
        @Override
        public String toString() {
            return "column";
        }
    },
    Square {
        @Override
        public String toString() {
            return "square";
        }
    },
    Exception {
        @Override
        public String toString() {
            return "exception";
        }
    }
}
