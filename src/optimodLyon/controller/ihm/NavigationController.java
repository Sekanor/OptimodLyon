package optimodLyon.controller.ihm;

import optimodLyon.IHM.NavigationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.*;

/**
 * Classe qui permet de donner un comportement aux différents boutons
 * de la NavigationView
 * @author Dorian TERBAH
 * @since 1.0
 */
public class NavigationController implements ActionListener
{
    /**
     * Référence vers la vue controlée par ce controleur
     */
    private final NavigationView view;

    /**
     * Composant pour afficher un système de fichier
     */
    private final JFileChooser fileChooser;

    /**
     * Constructeur de la classe NavigationController
     * @param view La Navigationview associée au controleur
     * @param buttons Les boutons qui seront controlés par ce controleur
     */
    public NavigationController(NavigationView view, List<JButton> buttons)
    {
        this.view = view;
        this.fileChooser = new JFileChooser();

        for (JButton button : buttons)
        {
            button.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        String actionCommand = event.getActionCommand();

        if (actionCommand.equals(NavigationView.LOAD_MAP_ACTION_COMMAND))
        {
            // Chargement du fichier de map
            int result = this.fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = this.fileChooser.getSelectedFile();
                if (!this.view.sendFileToWindow(NavigationView.FileType.MAP, selectedFile.getAbsolutePath()))
                {
                    String message = String.format("Le fichier de map %s n'a pas été correctement chargé", selectedFile.getName());
                    JOptionPane.showMessageDialog(null, message,"Erreur de chargement de la carte",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Carte chargée avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
                    this.view.setState(NavigationView.NavigationViewState.MAP_LOADED);
                }
            }
        }
        else if (actionCommand.equals(NavigationView.LOAD_PROGRAM_ACTION_COMMAND))
        {
            // Chargement du fichier de map
            int result = this.fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = this.fileChooser.getSelectedFile();
                if(!this.view.sendFileToWindow(NavigationView.FileType.DELIVERY_PLAN, selectedFile.getAbsolutePath()))
                {
                    String message = String.format("Le fichier d'inventaire %s n'a pas été correctement charger", selectedFile.getName());
                    JOptionPane.showMessageDialog(null, message,"Erreur de chargement de l'inventaire",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Inventaire chargé avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
                    this.view.setState(NavigationView.NavigationViewState.DELIVERY_PLAN_LOADED);
                }
            }
        }
        else if (actionCommand.equals(NavigationView.CALCULATE_CIRCUIT_ACTION_COMMAND))
        {
            // TODO: calcul avec les algos
        }
    }
}