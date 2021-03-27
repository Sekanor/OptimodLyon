package optimodLyon.model;

/**
 * Classe qui représente un segment de rue dans une carte.
 * @author Dorian TERBAH
 * @since 1.0
 */
public class Segment
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
     * @return L'intersection origine
     */
    public Intersection getOrigin()
    {
        return this.origin;
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
