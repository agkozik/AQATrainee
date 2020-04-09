public class MyArrayList {
    private String[] array = new String[10];
    private int size = 0;

    public void add(String str) {
        array[size] = str;
        size++;
        if (size == array.length) {
            String[] newArray = new String[(int) (array.length * 1.5 + 1)];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < array.length - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
        }
    }

    public void remove(String str) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (str.equals(array[i])) {
                index = i;
                break;
            }
        }
        if (index!=-1){
            remove(index);
        }
    }

    public int getSize() {
        return size;
    }

    public String get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        return "Element not found";
    }

    public static void main(String[] args) {
        MyArrayList arrayList = new MyArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        for (int i = 0; i < arrayList.size; i++) {
            System.out.println(arrayList.get(i));
        }
        arrayList.remove(2);
        System.out.println("-------------------------------------------");
        for (int i = 0; i < arrayList.size; i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println("-------------------------------------------");
        arrayList.remove("A");
        for (int i = 0; i < arrayList.size; i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println("-------------------------------------------");
        arrayList.remove(8);
        for (int i = 0; i < arrayList.size; i++) {
            System.out.println(arrayList.get(i));
        }
    }
}