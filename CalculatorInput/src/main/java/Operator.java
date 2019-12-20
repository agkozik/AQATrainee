class Operator {
    private boolean isFound = false;
    private int minusPosition;
    private char searchChar;

    Operator(int minusPosition, char searchChar) {
        this.minusPosition = minusPosition;
        this.searchChar = searchChar;
    }

    boolean isFound() {
        return isFound;
    }

    int getMinusPosition() {
        return minusPosition;
    }

    char getSearchChar() {
        return searchChar;
    }
}