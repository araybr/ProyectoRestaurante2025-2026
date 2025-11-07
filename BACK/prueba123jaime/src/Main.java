import model.Persona;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Persona juan = new Persona(1,"juan");
    Persona josu = new Persona(2,"josu");
    Persona jaime = new Persona(3,"jaime");

    Persona[] perso =  {juan,josu,jaime};

    List<Persona> personas = new ArrayList<>();
    Set<Persona> personasSet = new HashSet<>();


    personasSet.add(juan);
    personasSet.add(juan);

    personas.add(juan);
    personas.add(josu);
    personas.add(jaime);
    personas.add(jaime);

    System.out.println("Esto es una Lista");


    for(Persona persona : personas) {
        System.out.println(persona);
    }


    System.out.println();
    System.out.println("Esto es un Set: ");
    for(Persona persona : personasSet) {
        System.out.println(persona);
    }

}
