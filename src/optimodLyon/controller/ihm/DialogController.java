package optimodLyon.controller.ihm;

import optimodLyon.IHM.PickupDeliveryDialogView;
import optimodLyon.controller.Controller;
import optimodLyon.model.PickupAndDeliveryForm;
import optimodLyon.model.Segment;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

/**
 * Contrôleur qui gère le formulaire d'ajout d'un point de Pickup & Delivery
 *
 * @author Oumar Diakhaby
 * @since 1.0
 */
public class DialogController implements ItemListener, ActionListener, ChangeListener {
    /**
     * Champ qui permet de sélectionner la première rue composant le point de pickup
     */
    private JComboBox<Segment> pickupFirstWay;

    /**
     * Champ qui permet de sélectionner la deuxième rue composant le point de pickup
     */
    private JComboBox<Segment> pickupSecondWay;

    /**
     * Champ qui permet de sélectionner la première rue composant le point de delivery
     */
    private JComboBox<Segment> deliveryFirstWay;

    /**
     * Champ qui permet de sélectionner la deuxième rue composant le point de delivery
     */
    private JComboBox<Segment> deliverySecondWay;

    /**
     * Référence vers la popup d'ajout de point de Pickup & Delivery
     */
    private PickupDeliveryDialogView pickupDeliveyDialogView;

    /**
     * Champ qui permet de renseigner une durée de delivery
     */
    private JSpinner deliveryDurationSpinner;

    /**
     * Champ qui permet de renseigner une durée de pickup
     */
    private JSpinner pickupDurationSpinner;

    /**
     * Bouton pour ajouter le point de Pickup & Delivery
     */
    private JButton validateButton;

    /**
     * Modèle correspondant à l'ajout d'un point de Pickup & Delivery
     */
    PickupAndDeliveryForm pickupAndDeliveryForm;

    /**
     * Constructeur de la classe DialogController
     * @param pickupDeliveryDialogView Référence vers la popup d'ajout de point de Pickup & Delivery
     * @param okButton La référence du bouton pour valider l'ajout
     * @param cancelButton La référence du bouton pour annuler l'ajout
     */
    public DialogController(PickupDeliveryDialogView pickupDeliveryDialogView, JButton okButton, JButton cancelButton){
        this.pickupDeliveyDialogView = pickupDeliveryDialogView;

        this.pickupFirstWay = this.pickupDeliveyDialogView.getPickupFirstWay();
        this.pickupSecondWay = this.pickupDeliveyDialogView.getPickupSecondWay();
        this.deliveryFirstWay = this.pickupDeliveyDialogView.getDeliveryFirstWay();
        this.deliverySecondWay = this.pickupDeliveyDialogView.getDeliverySecondWay();

        this.deliveryDurationSpinner = this.pickupDeliveyDialogView.getDeliveryDurationSpinner();
        this.pickupDurationSpinner = this.pickupDeliveyDialogView.getPickupDurationSpinner();

        this.validateButton = okButton;

        this.pickupFirstWay.addItemListener(this);
        this.pickupSecondWay.addItemListener(this);
        this.deliveryFirstWay.addItemListener(this);
        this.deliverySecondWay.addItemListener(this);

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        this.pickupDurationSpinner.addChangeListener(this);
        this.deliveryDurationSpinner.addChangeListener(this);

        okButton.setEnabled(false);

        pickupAndDeliveryForm = new PickupAndDeliveryForm();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object o = e.getSource();
        if(o instanceof JComboBox)
        {
            JComboBox<Segment> comboBox = (JComboBox<Segment>) o;

            // Premiere voie pour le pickup
            if(comboBox.equals(this.pickupFirstWay) && this.pickupFirstWay.getSelectedIndex()!=0){
                Segment segmentRef = (Segment) comboBox.getSelectedItem();
                List<Segment> intersectionSegment = Controller.getInstance().getCityMap().getIntersectionSegments(segmentRef);
                this.pickupSecondWay.removeAllItems();
                for(Segment s : intersectionSegment){
                    this.pickupSecondWay.addItem(s);
                }
                this.pickupSecondWay.setEnabled(true);
                this.pickupAndDeliveryForm.setPickupFirstWay((Segment) comboBox.getSelectedItem());
                this.pickupAndDeliveryForm.setPickupSecondWay(intersectionSegment.get(0));

            // Deuxieme voie pour el pickup
            } else if(comboBox.equals(this.pickupSecondWay) && this.pickupSecondWay.getSelectedIndex()!=0){
                this.pickupAndDeliveryForm.setPickupSecondWay((Segment) comboBox.getSelectedItem());

            // Premiere voie pour le delivery
            } else if(comboBox.equals(this.deliveryFirstWay) && this.deliveryFirstWay.getSelectedIndex()!=0){
                Segment segmentRef = (Segment) comboBox.getSelectedItem();
                List<Segment> intersectionSegment = Controller.getInstance().getCityMap().getIntersectionSegments(segmentRef);
                this.deliverySecondWay.removeAllItems();
                for(Segment s : intersectionSegment){
                    this.deliverySecondWay.addItem(s);
                }
                this.deliverySecondWay.setEnabled(true);
                this.pickupAndDeliveryForm.setDeliveryFirstWay((Segment) comboBox.getSelectedItem());
                this.pickupAndDeliveryForm.setDeliverySecondWay(intersectionSegment.get(0));

                // Deuxieme voie pour le delivery
            } else if(comboBox.equals(this.deliverySecondWay) && this.pickupSecondWay.getSelectedIndex()!=0){
                this.pickupAndDeliveryForm.setDeliverySecondWay((Segment) comboBox.getSelectedItem());
            }
        }

        if (this.pickupAndDeliveryForm.isValid())
        {
            this.validateButton.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if(actionCommand.equals(PickupDeliveryDialogView.ACTION_OK)){
            Controller.getInstance().addRequest(this.pickupAndDeliveryForm);
            this.pickupDeliveyDialogView.dispose();
        } else if(actionCommand.equals(PickupDeliveryDialogView.ACTION_CANCEL)) {
            this.pickupDeliveyDialogView.dispose();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object o = e.getSource();
        if (o instanceof JSpinner)
        {
            JSpinner source = (JSpinner) o;
            if (source.equals(this.deliveryDurationSpinner))
            {
                this.pickupAndDeliveryForm.setDeliveryDuration((int) source.getValue());
            }
            else if (source.equals(this.pickupDurationSpinner))
            {
                this.pickupAndDeliveryForm.setPickupDuration((int) source.getValue());
            }
        }
    }
}
