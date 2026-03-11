public class Gato extends Animal{


    public Gato(String name) {
        super(name); //inicializa la clase padre, es animal
    }

    @Override
    public void hacerSonido(){
        System.out.println(name+ "hace miau");
    }
}
