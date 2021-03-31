package optimodLyon.controller.ihm;

import optimodLyon.IHM.LeftMenuView;
import optimodLyon.IHM.NavigationView;
import optimodLyon.IHM.PickupDeliveryDialogView;
import optimodLyon.controller.Controller;
import optimodLyon.model.Segment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class LeftMenuController implements ActionListener {

    /**
     * Référence vers la vue controlée par ce controleur
     */
    private final LeftMenuView view;

    private final Controller controller;


    /**
     * Constructeur de la classe NavigationController
     * @param view La Navigationview associée au controleur
     * @param buttons Les boutons qui seront controlés par ce controleur
     */
    public LeftMenuController(LeftMenuView view, List<JButton> buttons, final Controller controller)
    {
        this.view = view;
        this.controller = controller;
        for (JButton button : buttons)
        {
            button.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Renter");
        if(actionCommand.equals(LeftMenuView.ADD_PICKUP_AND_DELIVERY_POINTS)){
            System.out.println("Renter");
            PickupDeliveryDialogView pickupDeliveryDialogView = new PickupDeliveryDialogView(this.view.getWindow(), this.controller);
            pickupDeliveryDialogView.setVisible(true);
        } else if(actionCommand.equals(LeftMenuView.MODIFY_ITINERARY_ORDER)) {

        }
    }
}