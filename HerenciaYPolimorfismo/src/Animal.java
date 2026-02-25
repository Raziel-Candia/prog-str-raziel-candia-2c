public class Animal {

    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void comer(){
        System.out.println(name+ "esta comiendo");
    }
    public void haceSonidos(){
        System.out.println(name+ "esta haciendo un ruido x");
    }
}
