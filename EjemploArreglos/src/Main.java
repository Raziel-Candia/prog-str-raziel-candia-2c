//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


//Sintaxis

//Declaracion
        int[] arr;

        int[] arr1 = new int[4]; //Inicializacion
        int [] arr2= {1,2,3,4};

//Acceso (Get)
        System.out.println(arr1[0]);
        System.out.println(arr1[1]);

//Recorrido
        System.out.println("___________________");
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
        System.out.println("___________________");
        for(int numero : arr1){
            System.out.println(numero);
        }

        System.out.println("__________");
        Persona[] personas = new Persona[3];
        Persona persona1 = new Persona();
        persona1.setId(1);
        persona1.setName("Test");
        persona1.setActive(true);
        Persona persona2 = new Persona(2,"Eliel");
        Persona persona3 = new Persona(3,"David");

        personas[0]=persona1;
        personas[1]=persona2;
        personas[2]=persona3;

        personas[0]=null; //eliminar el primer elemento

        for(Persona persona: personas){
//System.out.println(persona);
            if(persona!=null && persona.isActive()){
                System.out.println("Hay un null");
            }else {
                System.out.println("_________");
                System.out.println(persona.getId());
                System.out.println(persona.getName());
                System.out.println(persona.isActive());
            }

        }


    }
}