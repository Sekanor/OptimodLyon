package optimodLyon.model;

import java.util.Objects;

/**
 * Classe qui représente un segment de rue dans une carte.
 * @author Dorian TERBAH
 * @since 1.0
 */
public class Segment implements Comparable<Segment>
{
    /**
     * Intersection destination du segment
     */
    private Intersection destination;

    /**
     * Intersection origine du segment
     */
    private Intersection origin;

    /**
     * Nom du segment
     */
    private String name;

    /**
     * Longueur du segment
     */
    private float length;


    /**
     * Constructeur de la classe Segment
     * @param name Le nom de la rue associé au segment
     */
    public Segment(String name){
        this.name = name;
    }

    /**
     * Constructeur de la classe Segment
     * @param destination L'intersection destination
     * @param origin L'intersection origine
     * @param name Le nom du segment
     * @param length La longueur du segment
     */
    public Segment(Intersection destination, Intersection origin, final String name, float length)
    {
        this.destination = destination;
        this.origin = origin;
        this.name = name;
        this.length = length;
    }

    /**
     * @return L'intersection destination
     */
    public Intersection getDestination()
    {
        return this.destination;
    }

    /**
     * @return La longueur du segment
     */
    public float getLength()
    {
        return length;
    }

    /**
     * @return Le nom du segment
     */
    public String getName()
    {
        return name;
    }

    /**
     * Affecte le nom du segment
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return L'intersection origine
     */
    public Intersection getOrigin()
    {
        return this.origin;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
        {
            return true;
        }
        else if (o == null)
        {
            return false;
        }
        else if (this.getClass() != o.getClass())
        {
            return false;
        }

        Segment s = (Segment) o;
        return Objects.equals(this.name, s.name);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int compareTo(Segment segment) {
        return this.getName().compareTo(segment.getName());
    }

    /**
     * Inverse l'ordre Origin/Destination du segment.
     */
    public void revertDirection()
    {
        Intersection destination = this.destination;
        this.destination = this.origin;
        this.origin = destination;
    }
}
