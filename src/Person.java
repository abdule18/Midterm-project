import java.util.Objects;

public class Person implements Comparable<Person> {
    private String name;
    private int birthYear;

    public Person() {
        this.name = "";
        this.birthYear = 0;
    }

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return birthYear == person.birthYear && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthYear);
    }

    @Override
    public String toString() {
        return String.format(
                "Person: Name: %30s | Birth Year: %4d" ,
                 this.name, this.birthYear
        );
    }

    @Override
    public int compareTo(Person p) {
        int nameCmp = p.getName().compareTo(this.getName());
        if (nameCmp != 0) {
            return nameCmp < 0 ? -1 : 1; // normalize
        }
        // If names equal: descending by birth year
        int yearCmp = Integer.compare(p.getBirthYear(), this.getBirthYear());
        return yearCmp < 0 ? -1 : (yearCmp > 0 ? 1 : 0);
    }
}
