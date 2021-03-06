package optimodLyon.controller.ihm;

import optimodLyon.IHM.LeftMenuView;
import optimodLyon.IHM.PickupDeliveryDialogView;
import optimodLyon.controller.Controller;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LeftMenuController implements ActionListener {

    /**
     * Référence vers la vue controlée par ce controleur
     */
    private final LeftMenuView view;

    /**
     * Constructeur de la classe NavigationController
     * @param view La Navigationview associée au controleur
     * @param buttons Les boutons qui seront controlés par ce controleur
     */
    public LeftMenuController(LeftMenuView view, List<JButton> buttons)
    {
        this.view = view;
        for (JButton button : buttons)
        {
            button.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if(actionCommand.equals(LeftMenuView.ADD_PICKUP_AND_DELIVERY_POINTS)){
            if(Controller.getInstance().getDeliveryPlan() != null){
                PickupDeliveryDialogView pickupDeliveryDialogView = new PickupDeliveryDialogView(this.view.getWindow());
                pickupDeliveryDialogView.setVisible(true);
            }

        }
        /*else if(actionCommand.equals(LeftMenuView.MODIFY_ITINERARY_ORDER)) {

        }*/
    }
}
