package optimodLyon.IHM;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Vue qui va contenir la légende de la carte
 * @author Dorian TERBAH
 * @since 1.0
 */
public class LegendView extends JPanel
{
    /**
     * Composant qui va contenir le titre de la légende
     */
    private JLabel legendNameLabel;

    /**
     * Composant qui va contenir la liste des icônes et leur label
     */
    private JPanel labelListPanel;

    /**
     * Composant qui va contenir l'icône pour les points de pickup
     */
    private JLabel pickupIconLabel;

    /**
     * Composant qui va contenir la légende pour les points de pickup
     */
    private JLabel pickupLabel;

    /**
     * Composant qui va contenir l'icône pour les points de delivery
     */
    private JLabel deliveryIconLabel;

    /**
     * Composant qui va contenir la légende pour les points de delivery
     */
    private JLabel deliveryLabel;

    /**
     * Composant qui va contenir l'icône pour le point de la warehouse
     */
    private JLabel warehouseIconLabel;

    /**
     * Composant qui va contenir la légende pour le point de la warehouse
     */
    private JLabel warehouseLabel;

    /**
     * Composant qui va contenir l'icône pour le circuit
     */
    private JLabel circuitIconLabel;

    /**
     * Composant qui va contenir la légende pour le circuit
     */
    private JLabel circuitLabel;


    /**
     * Label du titre du panneau Légende
     */
    private static final String LEGEND_LABEL = "Légende";

    /**
     * Label de la légende pour les points de pickup
     */
    private static final String PICKUP_LABEL = "Pickup";

    /**
     * Label de la légende pour les points de delivery
     */
    private static final String DELIVERY_LABEL = "Delivery";

    /**
     * Label de la légende pour le point de la warehouse
     */
    private static final String WAREHOUSE_LABEL = "Entrepôt des vélos";

    /**
     * Label de la légende pour le circuit
     */
    private static final String CIRCUIT_LABEL = "Cycliste n°1";

    /**
     * Chemin d'accés à l'image de localisation d'un point de pickup
     */
    private static final String PICKUP_IMAGE_PATH = "/image/pickup-localisation.png";

    /**
     * Chemin d'accés à l'image de localisation d'un point de delivery
     */
    private static final String DELIVERY_IMAGE_PATH = "/image/delivery-localisation.png";

    /**
     * Chemin d'accés à l'image de localisation d'un point de delivery
     */
    private static final String WAREHOUSE_IMAGE_PATH = "/image/warehouse.png";

    /**
     * Chemin d'accés à l'image du circuit
     */
    private static final String CIRCUIT_IMAGE_PATH = "/image/circuit.png";

    /**
     * Référence vers la fenetre de l'application
     */
    private final OptimodFrame window;

    /**
     * Constructeur de la classe NavigationView
     * @param window La fenetre de l'application
     */
    public LegendView(final OptimodFrame window)
    {
        super(new GridBagLayout());
        this.window = window;

        // Pickup label
        try {
            // Name label
            this.legendNameLabel = new JLabel();
            this.legendNameLabel.setText(LEGEND_LABEL);
            this.legendNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
            this.legendNameLabel.setForeground(new Color(0x284158));
            this.legendNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Label list panel
            this.labelListPanel = new JPanel();
            this.labelListPanel.setLayout(new GridBagLayout());
            this.labelListPanel.setBackground(null);

            // Pickup icon
            BufferedImage bigPickupIconImage = ImageIO.read(getClass().getResource(PICKUP_IMAGE_PATH));
            BufferedImage pickupIconImage = this.resize(bigPickupIconImage, 30, 30);
            ImageIcon pickupIcon = new ImageIcon(pickupIconImage);
            this.pickupIconLabel = new JLabel(pickupIcon);

            // Pickup label
            this.pickupLabel = new JLabel();
            this.pickupLabel.setText(PICKUP_LABEL);
            this.pickupLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            // Delivery icon
            BufferedImage bigDeliveryIconImage = ImageIO.read(getClass().getResource(DELIVERY_IMAGE_PATH));
            BufferedImage deliveryIconImage = this.resize(bigDeliveryIconImage, 30, 30);
            ImageIcon deliveryIcon = new ImageIcon(deliveryIconImage);
            this.deliveryIconLabel = new JLabel(deliveryIcon);

            // Delivery label
            this.deliveryLabel = new JLabel();
            this.deliveryLabel.setText(DELIVERY_LABEL);
            this.deliveryLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            // Pickup icon
            BufferedImage bigWarehouseIconImage = ImageIO.read(getClass().getResource(WAREHOUSE_IMAGE_PATH));
            BufferedImage warehouseIconImage = this.resize(bigWarehouseIconImage, 30, 30);
            ImageIcon warehouseIcon = new ImageIcon(warehouseIconImage);
            this.warehouseIconLabel = new JLabel(warehouseIcon);

            // Pickup label
            this.warehouseLabel = new JLabel();
            this.warehouseLabel.setText(WAREHOUSE_LABEL);
            this.warehouseLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            // Circuit icon
            BufferedImage bigCircuitIconImage = ImageIO.read(getClass().getResource(CIRCUIT_IMAGE_PATH));
            BufferedImage circuitIconImage = this.resize(bigCircuitIconImage, 30, 30);
            ImageIcon circuitIcon = new ImageIcon(circuitIconImage);
            this.circuitIconLabel = new JLabel(circuitIcon);

            // Circuit label
            this.circuitLabel = new JLabel();
            this.circuitLabel.setText(CIRCUIT_LABEL);
            this.circuitLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            // Add labels to label list panel
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(5, 5, 5, 5);
            c.gridy = 0;
            c.gridx = 0;
            this.labelListPanel.add(pickupIconLabel, c);
            c.gridy++;
            this.labelListPanel.add(deliveryIconLabel, c);
            c.gridy++;
            this.labelListPanel.add(warehouseIconLabel, c);
            c.gridy++;
            this.labelListPanel.add(circuitIconLabel, c);
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx++;
            c.gridy = 0;
            this.labelListPanel.add(pickupLabel, c);
            c.gridy++;
            this.labelListPanel.add(deliveryLabel, c);
            c.gridy++;
            this.labelListPanel.add(warehouseLabel, c);
            c.gridy++;
            this.labelListPanel.add(circuitLabel, c);

            // Add component to the legend panel
            c = new GridBagConstraints();
            c.insets = new Insets(10, 10, 10, 10);
            c.gridy = 0;
            this.add(this.legendNameLabel, c);
            c.gridy++;
            this.add(this.labelListPanel, c);
        } catch (Exception e) {
            System.err.println(e);
            JLabel errorLabel = new JLabel("Erreur de chargement des images");
            this.add(errorLabel);
        }
    }

    /**
     * Fonction qui permet de redimensionner une image
     * @param img L'image à redimensionner
     * @param newW La nouvelle largeur de l'image
     * @param newH La nouvelle hauteur de l'image
     * @return L'image redimensionnée avec la nouvelle largeur et la nouvelle hauteur
     */
    private BufferedImage resize(BufferedImage img, int newW, int newH)
    {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
