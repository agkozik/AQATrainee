public class ThrowExcInCatch {
    public static void main(String[] args) throws Exception {
        try{
            int result = getFactorial(-4);
String name;
            System.out.println(result);
        }
        catch(Exception ex){
            throw new Exception("The number is less than 1");
        }
        finally {
            System.out.println("Finally");
        }
    }

    public static int getFactorial(int num) throws Exception{
        if(num<1) throw new Exception(" 1");
        int result=1;
        for(int i=1; i<=num;i++){
            result*=i;
        }
        return result;
    }
}