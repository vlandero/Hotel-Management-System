abstract class X{
    protected int x;
    abstract void show();
    protected X(int a){
        x = a;
    }
}
class Y extends X{
    int y;
    void show(){
        System.out.println(Integer.toString(x) + ' ' + Integer.toString(y));
    }
    public Y(int a, int b){
        super(a);
        y = b;
    }
}
class Z extends X{
    int z;
    void show(){
        System.out.println(Integer.toString(x) + ' ' + Integer.toString(z));;
    }
    public Z(int a, int b){
        super(a);
        z = b;
    }
}

public class Test {
    class SS{
        private int a;

        public void setA(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }

        public SS(int a){
            this.a = a;
        }
    }
    public void tst(SS a){
        a.setA(2);
    }
    public void test(){
        X k = new Z(1,2);
        k.show();
    }
}
