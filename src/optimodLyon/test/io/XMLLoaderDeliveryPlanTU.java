package optimodLyon.test.io;

import optimodLyon.model.CityMap;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

import optimodLyon.io.*;
import optimodLyon.model.DeliveryPlan;
import optimodLyon.model.Request;

import static org.junit.Assert.*;

/**
 * Classe de test qui va tester le parsing d'un fichier d'inventaire de pickup-delivery
 */
public class XMLLoaderDeliveryPlanTU
{
    private static final String DELIVERY_FILES_PATH = "./rsc/test/io/delivery-files";

    private CityMap cityMap;

    @Before
    public void loadCityMap()
    {

        try
        {
            this.cityMap = XMLLoader.loadMap(new File(DELIVERY_FILES_PATH, "map.xml").getAbsolutePath());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            fail("Impossible de charger la map");
        }
    }

    /**
     * Méthode qui test le cas où le fichier donné en paramètre n'existe pas
     */
    @Test
    public void testFileNotFoundException()
    {
        DeliveryPlan plan;
        // Fichier qui n'existe pas
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, new File(DELIVERY_FILES_PATH, "unexisting.xml").getAbsolutePath());
            fail("Error expected : FileNotFoundException");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assertTrue(e instanceof FileNotFoundException);
        }
    }

    /**
     * Méthode qui teste si un répertoire donné en paramètre provoque une erreur
     */
    @Test
    public void testFileAsFolder()
    {
        DeliveryPlan plan;
        // Fichier qui existe mais qui est un répertoire
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, new File(DELIVERY_FILES_PATH).getAbsolutePath());
            fail("Error expected : IOException");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            assertTrue(e instanceof IOException);
        }
    }

    /**
     * Méthode qui teste tous les cas d'erreurs pour l'attribut de depot
     */
    @Test
    public void testDepotNode()
    {
        DeliveryPlan plan;
        String path = new File(DELIVERY_FILES_PATH, "delivery1.xml").getAbsolutePath();
        String message = "";

        // Noeud depot qui n'existe pas
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, path);
            fail(String.format("Error expected : Les informations concernant le dépôt sont manquantes dans le fichier %s", path));
        }
        catch (Exception e)
        {
            message = e.getMessage();
            System.out.println(message);
            assertEquals(String.format("Les informations concernant le dépôt sont manquantes dans le fichier %s", path), message);
        }

        path = new File(DELIVERY_FILES_PATH, "delivery2.xml").getAbsolutePath();
        // Attribut address qui n'est pas présent
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, path);
            fail(String.format("Error expected : L'attribut address est manquant dans les informations du dépôt dans le fichier %s", path));
        }
        catch (Exception e)
        {
            message = e.getMessage();
            System.out.println(message);
            assertEquals(String.format("L'attribut address est manquant dans les informations du dépôt dans le fichier %s", path), message);
        }

        path = new File(DELIVERY_FILES_PATH, "delivery3.xml").getAbsolutePath();
        // Attribut departureTime qui n'existe pas
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, path);
            fail(String.format("Error expected : L'attribut de la date de départ n'est pas présent dans le fichier %s", path));
        }
        catch (Exception e)
        {
            message = e.getMessage();
            System.out.println(message);
            assertEquals(String.format("L'attribut de la date de départ n'est pas présent dans le fichier %s", path), message);
        }
    }

    /**
     * Méthode qui test tous les cas d'erreurs pour une requete
     */
    @Test
    public void testRequest()
    {
        DeliveryPlan plan;
        String path = new File(DELIVERY_FILES_PATH, "delivery4.xml").getAbsolutePath();
        String message = "";

        // Attribut pickupAddress manquant
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, path);
            fail(String.format("Error expected : L'attribut pickupAddress est manquant pour une requête dans le fichier %s", path));
        }
        catch (Exception e)
        {
            message = e.getMessage();
            System.out.println(message);
            assertEquals(String.format("L'attribut pickupAddress est manquant pour une requête dans le fichier %s", path), message);
        }

        path = new File(DELIVERY_FILES_PATH, "delivery5.xml").getAbsolutePath();

        // Attribut deliveryAddress manquant
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, path);
            fail(String.format("Error expected : L'attribut deliveryAddress est manquant pour une requête dans le fichier %s", path));
        }
        catch (Exception e)
        {
            message = e.getMessage();
            System.out.println(message);
            assertEquals(String.format("L'attribut deliveryAddress est manquant pour une requête dans le fichier %s", path), message);
        }

        path = new File(DELIVERY_FILES_PATH, "delivery6.xml").getAbsolutePath();

        // Attribut pickupDuration
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, path);
            fail(String.format("Error expected : L'attribut pickupDuration est manquant pour une requête dans le fichier %s", path));
        }
        catch (Exception e)
        {
            message = e.getMessage();
            System.out.println(message);
            assertEquals(String.format("L'attribut pickupDuration est manquant pour une requête dans le fichier %s", path), message);
        }

        path = new File(DELIVERY_FILES_PATH, "delivery7.xml").getAbsolutePath();

        // Attribut deliveryDuration
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, path);
            fail(String.format("Error expected : L'attribut deliveryDuration est manquant pour une requête dans le fichier %s", path));
        }
        catch (Exception e)
        {
            message = e.getMessage();
            System.out.println(message);
            assertEquals(String.format("L'attribut deliveryDuration est manquant pour une requête dans le fichier %s", path), message);
        }
    }

    /**
     * Méthode qui teste les bonnes informations d'un deliveryPlan
     */
    @Test
    public void testDeliveryPlan()
    {
        DeliveryPlan plan = null;
        String path = new File(DELIVERY_FILES_PATH, "delivery-good-formed1.xml").getAbsolutePath();

        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, path);

            // vérification des informations du depot
            assertEquals(342873658, plan.getDepotAddressId());
            assertEquals(Time.valueOf("8:0:0"), plan.getDepartureTime());

            // vérification du nombre de requête dans l'inventaire
            List<Request> requests = plan.getRequests();
            assertEquals(1, requests.size());

            Request request = requests.get(0);

            // vérification des informations de la requête
            assertEquals(208769039, request.getPickup().getIntersection().getId());
            assertEquals(25173820, request.getDelivery().getIntersection().getId());
            assertEquals(180, request.getPickup().getDuration());
            assertEquals(240, request.getDelivery().getDuration());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("Le fichier est correct donc il n'est pas censé y avoir d'exception");
        }

        // tests sur un deuxieme fichier
        path = new File(DELIVERY_FILES_PATH, "delivery-good-formed2.xml").getAbsolutePath();
        try
        {
            plan = XMLLoader.loadDeliveryPlan(this.cityMap, path);// vérification des informations du depot

            assertEquals(25327124, plan.getDepotAddressId());
            assertEquals(Time.valueOf("8:0:0"), plan.getDepartureTime());

            List<Request> requests = plan.getRequests();
            assertEquals(9, requests.size());

            Request lastRequest = requests.get(requests.size() - 1);

            // vérification des informations de la requête
            assertEquals(1362204817, lastRequest.getPickup().getIntersection().getId());
            assertEquals(843129906, lastRequest.getDelivery().getIntersection().getId());
            assertEquals(180, lastRequest.getPickup().getDuration());
            assertEquals(540, lastRequest.getDelivery().getDuration());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("Le fichier est correct donc il n'est pas censé y avoir d'exception");
        }
    }
}
