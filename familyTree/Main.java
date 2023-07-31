package familyTree;

import familyTree.human.Gender;
import familyTree.human.Human;

import java.time.LocalDate;


public class Main  {
/*Продолжаем работать с проектом с семейным деревом.
Реализовать интерфейс Iterable для дерева.
Создать методы сортировки списка людей перед выводом, например по имени или по дате рождения (не менее 2)
Создать пакетную структуру для проекта */


public static void main(String[] args) {

FamilyTree tree = testTree();
System.out.println(tree);
FileHandler f1=new FileHandler();

// Сериализация объекта в файл

f1.serializeObject(tree, "person.out");

// Десериализация объекта из файла
FamilyTree treeRestored = (FamilyTree) f1.deserializeObject("person.out");
System.out.println(treeRestored);
tree.SortByName();
System.out.println(tree);
tree.SortByBirthDate();
System.out.println(tree);

}
static FamilyTree testTree() {
    FamilyTree tree = new FamilyTree();
    Human Sasha = new Human("Александр", Gender.Male, LocalDate.of(1976, 9, 19));
    Human Lena = new Human("Елена", Gender.Female, LocalDate.of(1987, 3, 1));
    tree.add(Sasha);
    tree.add(Lena);
    tree.setWedding(Sasha.getId(), Lena.getId());

    Human Nadya = new Human("Надежда", Gender.Female, LocalDate.of(2023, 12, 10), Sasha, Lena);
    Human Gena = new Human("Геннадий", Gender.Male, LocalDate.of(2022, 1, 14), Sasha, Lena);
    tree.add(Nadya);
    tree.add(Gena);

    Human Nina = new Human("Нина", Gender.Female, LocalDate.of(1942, 7, 8));
    tree.add(Nina);
    return tree;
}
  
    
}






