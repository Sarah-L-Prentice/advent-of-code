package web.prentice.aoc.common;

public enum Number {

    ONE {
        public int getInt() {
            return 1;
        }
    },
    TWO {
        @Override
        public int getInt() {
            return 2;
        }
    },
    THREE {
        @Override
        public int getInt() {
            return 3;
        }
    }, FOUR {
        @Override
        public int getInt() {
            return 4;
        }
    }, FIVE {
        @Override
        public int getInt() {
            return 5;
        }
    }, SIX {
        @Override
        public int getInt() {
            return 6;
        }
    }, SEVEN {
        @Override
        public int getInt() {
            return 7;
        }
    }, EIGHT {
        @Override
        public int getInt() {
            return 8;
        }
    }, NINE {
        @Override
        public int getInt() {
            return 9;
        }
    };

    public abstract int getInt();
}
