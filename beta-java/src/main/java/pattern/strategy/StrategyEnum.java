package pattern.strategy;

public enum  StrategyEnum {
    ADD {
        @Override
        public int calculate(int a, int b) {
            return a + b;
        }
    },

    SUBTRACT {
        @Override
        public int calculate(int a, int b) {
            return a - b;
        }
    };

    public abstract int calculate(int a, int b);

    public static void main(String[] args) {
        // 3
        int addResult = StrategyEnum.ADD.calculate(1, 2);
        System.out.println(addResult);

        // -1
        int subResult = StrategyEnum.SUBTRACT.calculate(1, 2);
        System.out.println(subResult);

    }
}